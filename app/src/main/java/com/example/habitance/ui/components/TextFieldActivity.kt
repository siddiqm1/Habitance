package com.example.habitance.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habitance.ui.theme.BottomText
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
        label = { Text(label, color = TextDark,fontSize = 13.sp) },
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.colors().copy(
            focusedContainerColor = BottomText,
            unfocusedContainerColor = BottomText,
            cursorColor = Color.Black,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        textStyle = LocalTextStyle.current.copy(color = Color.Black)
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Preview
@Composable
fun TextFieldActivityPreview() {
    TextFieldActivity(label = "Activity", value = "", onValueChange = {})
}
