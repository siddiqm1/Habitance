package com.example.habitance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.habitance.ui.theme.HabitanceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fontFamily = FontFamily(
            Font(R.font.lexend_bold, FontWeight.Bold),
            Font(R.font.lexend_thin, FontWeight.Thin),
            Font(R.font.lexend_black, FontWeight.Black),
            Font(R.font.lexend_light, FontWeight.Light),
            Font(R.font.lexend_medium, FontWeight.Medium),
            Font(R.font.lexend_extrabold, FontWeight.ExtraBold),
            Font(R.font.lexend_extralight, FontWeight.ExtraLight),
            Font(R.font.lexend_regular, FontWeight.Normal),
            Font(R.font.lexend_semibold, FontWeight.SemiBold),
            )
        enableEdgeToEdge()
        setContent {
            HabitanceTheme {
                AddActivityScreen()
            }
        }
    }
}