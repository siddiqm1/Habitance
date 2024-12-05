package com.example.habitance.ui.screens.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.habitance.R
import com.example.habitance.navbar.Screen

@Composable
fun NotificationScreen(navController: NavHostController) {
   Column (){
       Text(
           text = "Notification Screen",
           style = TextStyle(fontSize = MaterialTheme.typography.headlineMedium.fontSize),
           modifier = Modifier.padding(16.dp)
       )
       IconButton(
           onClick = { navController.navigate(Screen.HomeScreen.route) },
           modifier = Modifier.padding(horizontal = 8.dp)
       ) {
           Image(
               painter = painterResource(id = R.drawable.baseline_arrow_back_24),
               contentDescription = null,
               modifier = Modifier
                   .size(25.dp)
           )
       }
   }
}
@Composable
@Preview
fun NotificationScreenPreview() {
    NotificationScreen(navController = NavHostController(LocalContext.current))
}