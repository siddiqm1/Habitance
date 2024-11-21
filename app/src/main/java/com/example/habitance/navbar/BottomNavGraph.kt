package com.example.habitance.navbar

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.BeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habitance.screen.Note.NotePage
import com.example.habitance.screen.activity.ActivityList
import com.example.habitance.screen.finished.FinishedPage
import com.example.habitance.screen.home.HomePage

@Composable
fun BottomNavGraph(navHostController: NavController) {
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
                    HomePage(navHostController)
                }
                composable(route = BottomBarScreen.Activity.route) {
                    ActivityList()
                }
                composable(route = BottomBarScreen.Note.route) {
                    NotePage()
                }
                composable(route = BottomBarScreen.FinisActivity.route) {
                    FinishedPage()
                }
            }
        }
    }
}






