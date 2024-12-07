package com.example.habitance.ui.screens.auth.login

import android.content.Context
import com.example.habitance.function.AuthViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(
    context: Context
) : AuthViewModel(context) {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _isPasswordVisible = MutableStateFlow(false)
    val isPasswordVisible = _isPasswordVisible.asStateFlow()

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
        _isLoading.value = true
        _errorMessage.value = null
        authManager.loginWithEmailAndPassword(
            _email.value,
            _password.value,
            onSuccess = {
                _isLoading.value = false
                onLoginSuccess()
            },
            onError = { error ->
                _isLoading.value = false
                _errorMessage.value = error
            }
        )
    }
}
