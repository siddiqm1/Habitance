package com.example.habitance.ui.components

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habitance.ui.theme.Border
import com.example.habitance.ui.theme.BottomText
import com.example.habitance.ui.theme.TextDark
import com.example.habitance.ui.theme.fontFamily

@Composable
fun DatePicker(){
    Column {
        Text(
            text = "Waktu Mulai dan Selesai",
            fontSize = 16.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight(500),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier
            .fillMaxWidth()){

            Button(modifier = Modifier
                .padding(8.dp)
                .weight(1f),
                colors = ButtonDefaults.buttonColors(BottomText),
                border = BorderStroke(1.dp, Border),
                onClick = {}) {
                Text(text = "Mulai", modifier = Modifier.padding(vertical = 3.dp, horizontal = 16.dp),
                    fontFamily = fontFamily, fontSize = 14.sp, fontWeight = FontWeight(400), color = TextDark
                )
            }

            Button(modifier = Modifier
                .padding(8.dp)
                .weight(1f),
                colors = ButtonDefaults.buttonColors(BottomText),
                border = BorderStroke(1.dp, Border),
                onClick = {}) {
                Text(text = "Selesai", modifier = Modifier.padding(vertical = 3.dp, horizontal = 16.dp),
                    fontFamily = fontFamily, fontSize = 14.sp, fontWeight = FontWeight(400), color = TextDark
                )
            }
        }
    }
}
