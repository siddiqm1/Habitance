package com.example.habitance.screen.auth.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.habitance.AuthManager


@Composable
fun RegisterPage(
    modifier: Modifier = Modifier,
    authManager: NavHostController,
    navController: AuthManager
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Home Screen", fontSize = 20.sp)
    }
}