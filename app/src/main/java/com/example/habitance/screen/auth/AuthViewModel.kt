package com.example.habitance.screen.auth

import android.content.Context
import androidx.credentials.Credential
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitance.AuthManager
import kotlinx.coroutines.launch

open class AuthViewModel(
    context : Context
) : ViewModel() {

    protected val authManager = AuthManager(context)

    fun signInWithGoogle(
        onSuccess: () -> Unit
    ){
        viewModelScope.launch{
            authManager.signInWithGoogle{
                onSuccess()
            }
        }
    }

}