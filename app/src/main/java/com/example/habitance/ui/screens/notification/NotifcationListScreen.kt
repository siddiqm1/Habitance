package com.example.habitance.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.habitance.R
import com.example.habitance.ui.screens.notification.Notification
import com.example.habitance.ui.screens.notification.NotificationViewModel

@Composable
fun NotificationListScreen(
    navController: NavController,
    viewModel: NotificationViewModel
) {
    val notifications = viewModel.notifications.collectAsState()
    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_notification") },
                backgroundColor = Color(0xFF16423C),
                contentColor = Color.White
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_alarm),
                    contentDescription = "Add Alarm"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        backgroundColor = Color(0xFFF5F5F5)
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            items(notifications.value) { notification ->
                NotificationCard(
                    notification = notification,
                    onToggle = { isEnabled ->
                        viewModel.updateNotificationState(notification.id, isEnabled)
                    },
                    onDelete = {
                        viewModel.deleteNotification(notification.id, context) // Berikan context ke ViewModel
                    }
                )
            }
        }
    }
}


@Composable
fun NotificationCard(
    notification: Notification,
    onToggle: (Boolean) -> Unit,
    onDelete: () -> Unit
) {
    var isEnabled by remember { mutableStateOf(notification.isEnabled) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 4.dp,
        backgroundColor = Color.White
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = notification.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = notification.description,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = notification.time,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(
                    checked = isEnabled,
                    onCheckedChange = {
                        isEnabled = it
                        onToggle(it)
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color(0xFF6A9C89),
                        uncheckedThumbColor = Color.Gray
                    )
                )
                IconButton(onClick = { onDelete() }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Alarm",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}
