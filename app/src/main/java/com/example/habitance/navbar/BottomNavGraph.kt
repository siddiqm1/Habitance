package com.example.habitance.navbar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.habitance.Screen.Activity.ActivityList
import com.example.habitance.Screen.HomePage
import com.example.habitance.Screen.Note.NotePage
import com.example.habitance.Screen.Auth.RegisterPage
import com.example.habitance.Screen.FinisActivity.FinisPage

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route)
        {
           HomePage()
        }
        composable(route = BottomBarScreen.Activity.route)
        {
            ActivityList()

        }
        composable(route = BottomBarScreen.FinisActivity.route)
        {
            FinisPage()
        }
        composable(route = BottomBarScreen.Note.route)
        {
            NotePage()
        }
    }
}


