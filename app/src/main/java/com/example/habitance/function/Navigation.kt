package com.example.habitance.function


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habitance.activity.LoginPage
import com.example.habitance.navbar.BottomNavGraph
import com.example.habitance.navbar.NavBar
import com.example.habitance.screen.auth.register.RegisterScreen
import com.example.habitance.screen.auth.sign_up.SignupPage
import com.example.habitance.screen.home.HomePage


@Composable
fun Navigation( modifier : Modifier = Modifier, authManager: AuthManager){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "register"
    ) {
        composable("login"){
           LoginPage(modifier, navController)
        }
        composable("signup"){
            SignupPage(modifier, navController)
        }
        composable("register"){
           RegisterScreen(modifier, navController)
        }
        composable("home"){
            BottomNavGraph(navController)
        }
    }
}