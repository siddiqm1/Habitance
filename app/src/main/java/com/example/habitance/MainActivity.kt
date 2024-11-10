package com.example.habitance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier

import com.example.habitance.ui.theme.HabitanceTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel : AuthViewModel by viewModels()
        setContent {
           HabitanceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()){
                        innerPadding -> Navigation(modifier = Modifier.padding(innerPadding), authViewModel = AuthViewModel())
                }



            }
        }
    }
}
