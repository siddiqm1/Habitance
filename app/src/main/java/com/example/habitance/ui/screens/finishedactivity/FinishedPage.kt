package com.example.habitance.ui.screens.finishedactivity

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.habitance.ui.components.ListEmpty

@Composable
fun FinishedActivityEmpty(navController: NavController){
    ListEmpty("Finished Activity",navController)
}



