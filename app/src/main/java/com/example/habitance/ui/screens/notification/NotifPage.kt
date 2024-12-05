package com.example.habitance.ui.screens

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.habitance.ui.screens.notification.Notification
import com.example.habitance.ui.screens.notification.NotificationViewModel
import java.util.Calendar

@Composable
fun AddNotificationScreen(navController: NavController, viewModel: NotificationViewModel) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Judul") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Deskripsi") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                showTimePicker(context) { selectedTime ->
                    time = selectedTime
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (time.isEmpty()) "Pilih Waktu" else "Waktu: $time")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (title.isNotEmpty() && description.isNotEmpty() && time.isNotEmpty()) {
                    val notification = Notification(
                        id = "",
                        title = title,
                        description = description,
                        time = time
                    )
                    viewModel.addNotification(notification)
//                    navController.navigate("notification_list")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan Notifikasi")
        }
    }
}

fun showTimePicker(context: Context, onTimeSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val timePicker = TimePickerDialog(
        context,
        { _, hour, minute ->
            onTimeSelected(String.format("%02d:%02d", hour, minute))
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true
    )
    timePicker.show()
}
