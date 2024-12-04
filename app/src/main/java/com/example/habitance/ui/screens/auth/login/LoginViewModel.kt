package com.example.habitance.ui.screens.auth.login

import android.content.Context
import com.example.habitance.function.AuthViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(
    context: Context
) : AuthViewModel(context) {

    // State flows for UI states
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _isPasswordVisible = MutableStateFlow(false)
    val isPasswordVisible = _isPasswordVisible.asStateFlow()

    // New state flows for loading and error
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    fun updateEmail(newEmail: String) {
        _email.value = newEmail.replace(" ", "")
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword.replace(" ", "")
    }

    fun togglePasswordVisibility() {
        _isPasswordVisible.value = !_isPasswordVisible.value
    }

    fun loginWithEmailAndPassword(
        onLoginSuccess: () -> Unit,
    ) {
        _isLoading.value = true // Set loading state
        _errorMessage.value = null // Clear previous errors
        authManager.loginWithEmailAndPassword(
            _email.value,
            _password.value,
            onSuccess = {
                _isLoading.value = false // Stop loading
                onLoginSuccess()
            },
            onError = { error ->
                _isLoading.value = false // Stop loading
                _errorMessage.value = error // Set error message
            }
        )
    }
}
