package com.example.habitance.ui.screens.notification.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habitance.R
import com.example.habitance.ui.screens.notification.Notification
import com.example.habitance.ui.theme.TextDark

@Composable
fun NotificationRow(
    notification: Notification,
    onToggle: (Boolean) -> Unit,
    onDelete: () -> Unit
) {
    var isEnabled by remember { mutableStateOf(notification.isEnabled) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xFFF8F8F8),
                shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Icon Alarm
        Icon(
            painter = painterResource(id = R.drawable.alarm),
            contentDescription = "Alarm Icon",
            tint = Color(0xFF16423C),
            modifier = Modifier.size(40.dp)
        )

        // Informasi Notifikasi
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = notification.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = (TextDark)
            )
            //Kita tampilkan des???? tolong comment bang fullsctack aka aji
//            Text(
//                text = notification.description,
//                fontSize = 14.sp,
//                color = Color.Gray,
//                modifier = Modifier.padding(top = 4.dp)
//            )
            Text(
                text = notification.time,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        IconButton(onClick = { onDelete() }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Alarm",
                tint = Color.Red
            )
        }

        androidx.compose.material.Switch(
            checked = isEnabled,
            onCheckedChange = { isChecked ->
                isEnabled = isChecked
                onToggle(isChecked)
            },
            colors = androidx.compose.material.SwitchDefaults.colors(
                checkedThumbColor = Color(0xFF16423C),
                uncheckedThumbColor = Color.Gray
            )
        )
    }
}