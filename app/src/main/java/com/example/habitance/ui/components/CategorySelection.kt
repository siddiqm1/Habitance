package com.example.habitance.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habitance.ui.theme.Border
import com.example.habitance.ui.theme.BottomText
import com.example.habitance.ui.theme.fontFamily

@Composable
fun CategorySelection(onCategorySelected: (String) -> Unit) {
    var category by rememberSaveable { mutableStateOf<String?>(null) }

    Text(
        text = "Kategori",
        fontSize = 16.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight(500),
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = {
                category = "Baik"
                onCategorySelected("Baik") // Memanggil callback
            },
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            elevation = ButtonDefaults.buttonElevation(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (category == "Baik") Border else BottomText
            )
        ) {
            Text("Baik", color = Color.Black, fontFamily = fontFamily, fontSize = 15.sp)
        }

        Button(
            onClick = {
                category = "Buruk"
                onCategorySelected("Buruk") // Memanggil callback
            },
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            elevation = ButtonDefaults.buttonElevation(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (category == "Buruk") Border else BottomText
            )
        ) {
            Text("Buruk", color = Color.Black, fontFamily = fontFamily, fontSize = 15.sp)
        }
    }
}
