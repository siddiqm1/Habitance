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
import com.example.habitance.ui.screens.activitylist.ActivityList
import com.example.habitance.ui.screens.note.NotePage
import com.example.habitance.ui.screens.activitylist.ActivityListEmpty
import com.example.habitance.ui.screens.addactivity.AddActivity
import com.example.habitance.ui.screens.finishedactivity.FinishedActivityEmpty

@Composable
fun MainNavigation(navHostController: NavController) {
    val navController = rememberNavController() // Inisialisasi NavController hanya sekali
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
                    ActivityListEmpty(navController)
                }
                composable(route = BottomBarScreen.Note.route) {
                    NotePage(navController)
                }
                composable(route = BottomBarScreen.FinisActivity.route) {
                    FinishedActivityEmpty(navController)
                }
                composable(route = Screen.HomeScreen.route){
                    HomePage(navController)
                }
                composable(route = Screen.NoteScreen.route){
                    NotePage(navController)
                }
                composable(route = Screen.AddActivityScreen.route){
                    AddActivity(navController)
                }
                composable(route = Screen.FinishedActivityEmpty.route){
                    FinishedActivityEmpty(navController)
                }
                composable(route = Screen.ActivityList.route){
                    ActivityList(navController)
                }

            }
        }
    }
}






