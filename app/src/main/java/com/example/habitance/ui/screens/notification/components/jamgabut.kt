package com.example.habitance.ui.screens.notification.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.util.Calendar

@Composable
fun AnalogClockSmall() {
    var currentTime by remember { mutableStateOf(Calendar.getInstance()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L)
            currentTime = Calendar.getInstance()
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(
            modifier = Modifier.size(120.dp)
        ) {
            val centerX = size.width / 2
            val centerY = size.height / 2
            val radius = size.minDimension / 2

            drawCircle(
                color = Color.Black,
                radius = radius,
                style = androidx.compose.ui.graphics.drawscope.Stroke(3.dp.toPx())
            )

            for (i in 0 until 12) {
                val angle = i * 30f
                val startX = centerX + (radius - 8.dp.toPx()) * Math.cos(Math.toRadians(angle.toDouble())).toFloat()
                val startY = centerY + (radius - 8.dp.toPx()) * Math.sin(Math.toRadians(angle.toDouble())).toFloat()
                val endX = centerX + (radius - 16.dp.toPx()) * Math.cos(Math.toRadians(angle.toDouble())).toFloat()
                val endY = centerY + (radius - 16.dp.toPx()) * Math.sin(Math.toRadians(angle.toDouble())).toFloat()
                drawLine(
                    color = Color.Black,
                    start = androidx.compose.ui.geometry.Offset(startX, startY),
                    end = androidx.compose.ui.geometry.Offset(endX, endY),
                    strokeWidth = 2.dp.toPx()
                )
            }

            val hour = currentTime.get(Calendar.HOUR)
            val minute = currentTime.get(Calendar.MINUTE)
            val second = currentTime.get(Calendar.SECOND)

            rotate(hour * 30f + minute * 0.5f, pivot = androidx.compose.ui.geometry.Offset(centerX, centerY)) {
                drawLine(
                    color = Color.Black,
                    start = androidx.compose.ui.geometry.Offset(centerX, centerY),
                    end = androidx.compose.ui.geometry.Offset(centerX, centerY - radius * 0.5f),
                    strokeWidth = 4.dp.toPx()
                )
            }

            rotate(minute * 6f, pivot = androidx.compose.ui.geometry.Offset(centerX, centerY)) {
                drawLine(
                    color = Color.Gray,
                    start = androidx.compose.ui.geometry.Offset(centerX, centerY),
                    end = androidx.compose.ui.geometry.Offset(centerX, centerY - radius * 0.7f),
                    strokeWidth = 3.dp.toPx()
                )
            }

            rotate(second * 6f, pivot = androidx.compose.ui.geometry.Offset(centerX, centerY)) {
                drawLine(
                    color = Color.Red,
                    start = androidx.compose.ui.geometry.Offset(centerX, centerY),
                    end = androidx.compose.ui.geometry.Offset(centerX, centerY - radius * 0.9f),
                    strokeWidth = 2.dp.toPx()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = String.format(
                "%02d:%02d:%02d",
                currentTime.get(Calendar.HOUR_OF_DAY),
                currentTime.get(Calendar.MINUTE),
                currentTime.get(Calendar.SECOND)
            ),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}
