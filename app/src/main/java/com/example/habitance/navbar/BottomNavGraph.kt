package com.example.habitance.navbar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.habitance.activity.ActivityList
import com.example.habitance.activity.CardView
import com.example.habitance.activity.HomePage
import com.example.habitance.activity.NotePage
import com.example.habitance.activity.RegisterPage

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
            CardView()
        }
        composable(route = BottomBarScreen.Register.route)
        {
            RegisterPage()
        }
        composable(route = BottomBarScreen.Note.route)
        {
            NotePage()
        }
    }
}


