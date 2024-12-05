package com.example.habitance.ui.screens

import android.service.notification.NotificationListenerService
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.habitance.ui.screens.notification.NotificationViewModel

@Composable
fun NotificationListScreen(navController: NavController, viewModel: NotificationViewModel) {
    val notifications = viewModel.notifications.collectAsState()

    Column {
        LazyColumn(modifier = Modifier.padding(8.dp)) {
            items(notifications.value) { notification ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Column {
                            Text("Judul: ${notification.title}")
                            Text("Waktu: ${notification.time}")
                        }
                        IconButton(onClick = {
                            viewModel.deleteNotification(notification.id)
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }
            }
        }
        IconButton(onClick = {
            navController.navigate("add_notification")
        }) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    }
}
