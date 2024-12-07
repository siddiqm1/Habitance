package com.example.habitance.ui.screens.auth.sign_up

import android.content.Context
import com.example.habitance.function.AuthViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpViewModel(
    context: Context
) : AuthViewModel(context) {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _isPasswordVisible = MutableStateFlow(false)
    val isPasswordVisible = _isPasswordVisible.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword = _confirmPassword.asStateFlow()

    private val _isConfirmPasswordVisible = MutableStateFlow(false)
    val isConfirmPasswordVisible = _isConfirmPasswordVisible.asStateFlow()

    private val _isLoading = MutableStateFlow(false) // Loading state
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null) // Error message state
    val errorMessage = _errorMessage.asStateFlow()

    fun updateEmail(newEmail: String) {
        _email.value = newEmail.replace(" ", "")
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword.replace(" ", "")
    }

    fun updateConfirmPassword(newPassword: String) {
        _confirmPassword.value = newPassword.replace(" ", "")
    }

    fun togglePasswordVisibility() {
        _isPasswordVisible.value = !_isPasswordVisible.value
    }

    fun toggleConfirmPasswordVisibility() {
        _isConfirmPasswordVisible.value = !_isConfirmPasswordVisible.value
    }

    fun signUpWithEmailAndPassword(onSignUpSuccess: () -> Unit) {
        _isLoading.value = true
        _errorMessage.value = null

        authManager.signUpWithEmailAndPassword(
            email = _email.value,
            password = _password.value,
            confirmPassword = _confirmPassword.value,
            onSuccess = {
                _isLoading.value = false
                onSignUpSuccess()
            },
            onError = { errorMessage ->
                _isLoading.value = false
                _errorMessage.value = errorMessage
            }
        )
    }
}
