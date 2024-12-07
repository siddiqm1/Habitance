package com.example.habitance.function

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.example.habitance.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.security.MessageDigest
import java.util.UUID

class AuthManager(private val context: Context) {
    private val auth = FirebaseAuth.getInstance()

    fun resetPassword(email: String): Flow<AuthResponse> = callbackFlow {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { resetTask ->
                if (resetTask.isSuccessful) {
                    trySend(AuthResponse.Success)
                } else {
                    if (resetTask.exception is FirebaseAuthInvalidUserException) {
                        trySend(AuthResponse.Error("Email tidak terdaftar"))
                    } else {
                        trySend(
                            AuthResponse.Error(
                                resetTask.exception?.message ?: "Error sending reset email"
                            )
                        )
                    }
                }
            }
        awaitClose()
    }

    fun loginWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (email.isBlank()) {
            onError("Email cannot be empty")
            return
        }
        if (password.isBlank()) {
            onError("Password cannot be empty")
            return
        }
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onSuccess()
                    } else {
                        val exception = task.exception
                        val errorMessage = when (exception) {
                            is FirebaseAuthInvalidUserException -> {
                                "Email not found. Please check again or register first."
                            }
                            is FirebaseAuthInvalidCredentialsException -> {
                                "Incorrect password. Please try again."
                            }
                            else -> {
                                exception?.message ?: "Login failed. Please try again later."
                            }
                        }
                        onError(errorMessage)
                    }
                }
        } catch (e: Exception) {
            onError(e.message ?: "An error occurred during login.")
        }
    }

    fun signUpWithEmailAndPassword(
        email: String,
        password: String,
        confirmPassword: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (email.isBlank()) {
            onError("Email cannot be empty")
            return
        }
        if (password.isBlank() || confirmPassword.isBlank()) {
            onError("Password cannot be empty")
            return
        }
        if (password != confirmPassword) {
            onError("Passwords do not match")
            return
        }
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        auth.currentUser!!.sendEmailVerification()
                            .addOnCompleteListener { verificationTask ->
                                if (verificationTask.isSuccessful) {
                                    onSuccess()
                                } else {
                                    onError("Failed to send verification email")
                                }
                            }
                    } else {
                        onError("Failed to create account: ${task.exception?.message}")
                    }
                }
        } catch (e: Exception) {
            onError("Failed to create account: ${e.message}")
        }
    }

    private fun createNonce(): String {
        val rawNonce = UUID.randomUUID().toString()
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)

        return digest.fold("") { str, it ->
            str + "%02x".format(it)
        }
    }

    fun signOut() {
        auth.signOut()
    }

    suspend fun signInWithGoogle(
        onLoginSuccess: () -> Unit
    ) {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(context.getString(R.string.web_client_id))
            .setAutoSelectEnabled(false)
            .setNonce(createNonce())
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()


        try {
            val credentialManager = CredentialManager.create(context)
            val result = credentialManager.getCredential(
                context = context,
                request = request
            )

            val credential = result.credential
            if (credential is CustomCredential) {
                if (credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleidTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)

                        val firebaseCredential = GoogleAuthProvider
                            .getCredential(
                                googleidTokenCredential.idToken,
                                null
                            )
                        auth.signInWithCredential(firebaseCredential)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    onLoginSuccess()
                                }
                            }
                    } catch (e: GoogleIdTokenParsingException) {
                        Log.e("error", e.toString())
                    }
                }
            }


        } catch (e: Exception) {
            Log.d("error", e.toString())
        }
    }
}

sealed interface AuthResponse {
    data object  Loading : AuthResponse
    data object Success : AuthResponse
    data class Error(val message: String) : AuthResponse
}
