package com.example.habitance.ui.screens.activitylist

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.habitance.ui.components.ListEmpty



@Composable
fun ActivityListEmpty(navController: NavHostController){
    ListEmpty("Add Activity", navController = navController)
}