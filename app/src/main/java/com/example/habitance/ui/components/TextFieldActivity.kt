package com.example.habitance.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habitance.ui.theme.TextDark
import com.example.habitance.ui.theme.fontFamily

@Composable
fun TextFieldActivity(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
){
    Text(
        text = label,
        fontSize = 16.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight(500),
    )
    Spacer(modifier = Modifier.height(8.dp))
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
            .clip(RoundedCornerShape(30.dp)) // Membulatkan sudut
            .background(Color.White) // Warna latar belakang
            .border(1.dp, TextDark, RoundedCornerShape(30.dp)), // Garis tepi
        maxLines = 35,
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
    Spacer(modifier = Modifier.height(16.dp))
}
