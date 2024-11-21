package com.example.habitance.navbar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.habitance.screen.activity.ActivityList
import com.example.habitance.screen.HomePage
import com.example.habitance.screen.Note.NotePage
import com.example.habitance.screen.FinisActivity.FinisPage

@Composable
fun BottomNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route)
        {
           HomePage(navController = navController)
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


