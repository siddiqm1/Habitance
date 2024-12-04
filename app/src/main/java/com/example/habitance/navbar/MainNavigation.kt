package com.example.habitance.navbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habitance.screen.home.HomePage
import com.example.habitance.ui.components.ListEmpty
import com.example.habitance.ui.screens.note.NotePage
import com.example.habitance.ui.screens.activitylist.ActivityScreen
import com.example.habitance.ui.screens.addactivity.AddActivity

@Composable
fun MainNavigation(navHostController: NavController) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomBar(navController)
        },
        containerColor = Color.White
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Pastikan konten tidak overlap dengan bottom bar
        ) {
            NavHost(
                navController = navController,
                startDestination = BottomBarScreen.Home.route,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(route = BottomBarScreen.Home.route) {
                    HomePage(navController)
                }
                composable(route = BottomBarScreen.Activity.route) {
                    ActivityScreen(navController)
                }
                composable(route = BottomBarScreen.Note.route) {
                    NotePage(navController)
                }
                composable(route = BottomBarScreen.FinisActivity.route) {
                    ListEmpty("Finished Activity", navController)
                }
                composable(route = Screen.AddActivityScreen.route){
                    AddActivity(
                     navController = navController
                    )
                }
                composable(route = Screen.ActivityListEmpty.route){
                    ActivityScreen(
                        navController
                    )
                }
            }
        }
    }
}






