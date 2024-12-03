package com.example.habitance.ui.screens.finishedactivity

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.habitance.ui.components.ListEmpty

@Composable
fun FinishedActivityEmpty(navController: NavHostController){
    ListEmpty("Finished Activity", navController = navController)
}

@Preview
@Composable
fun PreviewFinishedActivityEmpty(){
    FinishedActivityEmpty(navController = rememberNavController())
}


