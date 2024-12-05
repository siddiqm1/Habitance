package com.example.habitance.ui.screens.notification

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.habitance.navbar.Screen
import java.util.Calendar

@Composable
fun AddNotificationScreen(navController: NavController) {
    val viewModel: NotificationViewModel = hiltViewModel()
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Judul Notifikasi") },
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
                    val notification = NotificationModel(
                        id = "", // ID akan dihasilkan otomatis
                        title = title,
                        description = description,
                        time = time
                    )
                    viewModel.addNotification(notification)
                    navController.navigate(Screen.NotificationScreen.route) {
                        popUpTo(Screen.NotificationScreen.route) { inclusive = true }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan Notifikasi")
        }
    }
}

// Mengubah showTimePicker menjadi fungsi biasa
fun showTimePicker(context: android.content.Context, onTimeSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val timePicker = TimePickerDialog(
        context,
        { _, hour, minute ->
            val formattedTime = String.format("%02d:%02d", hour, minute)
            onTimeSelected(formattedTime)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true
    )
    timePicker.show()
}

@Preview
@Composable
fun AddNotificationScreenPreview() {
    AddNotificationScreen(navController = NavController(LocalContext.current))
}