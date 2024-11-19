package com.example.habitance

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


import com.example.habitance.screen.auth.register.RegisterPage

import com.example.habitance.screen.auth.sign_up.SignupPage
import com.example.habitance.activity.LoginPage
import com.example.habitance.screen.HomePage


@Composable
fun Navigation( modifier : Modifier = Modifier, authManager: AuthManager){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login"){
           LoginPage(modifier, navController)
        }
        composable("signup"){
            SignupPage(modifier, navController)
        }
        composable("register"){
           RegisterPage(modifier, navController,authManager)
        }
        composable("home"){
           HomePage(navController = navController)
        }
    }

}