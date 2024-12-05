package com.example.habitance.ui.components

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habitance.ui.theme.BottomText
import com.example.habitance.ui.theme.TextDark
import com.example.habitance.ui.theme.TextMedium
import com.example.habitance.ui.theme.fontFamily
import java.util.Calendar
import java.util.Date

import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DatePicker(onDateSelected: (start: String?, end: String?) -> Unit) {
    var startDate by remember { mutableStateOf<String?>(null) }
    var endDate by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()) // Tentukan format tanggal

    Column {
        Text(
            text = "Waktu Mulai dan Selesai",
            fontSize = 16.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight(500),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Button for selecting start date
            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(BottomText),
                border = BorderStroke(1.dp, TextMedium),
                onClick = {
                    showDatePickerDialog(context) { date ->
                        startDate = dateFormat.format(date)
                        onDateSelected(startDate, endDate) // Call the callback with updated dates
                    }
                }
            ) {
                Text(
                    text = startDate ?: "Mulai",
                    modifier = Modifier.padding(vertical = 3.dp, horizontal = 16.dp),
                    fontFamily = fontFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = TextDark
                )
            }

            // Button for selecting end date
            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(BottomText),
                border = BorderStroke(1.dp, TextMedium),
                onClick = {
                    showDatePickerDialog(context) { date ->
                        endDate = dateFormat.format(date)
                        onDateSelected(startDate, endDate) // Call the callback with updated dates
                    }
                }
            ) {
                Text(
                    text = endDate ?: "Selesai",
                    modifier = Modifier.padding(vertical = 3.dp, horizontal = 16.dp),
                    fontFamily = fontFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = TextDark
                )
            }
        }
    }
}

// Function to display date picker dialog
private fun showDatePickerDialog(context: Context, onDatePicked: (Date) -> Unit) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = Calendar.getInstance().apply {
                set(selectedYear, selectedMonth, selectedDay)
            }.time
            onDatePicked(selectedDate)
        },
        year,
        month,
        day
    ).show()
}


