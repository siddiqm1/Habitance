package com.example.habitance.screen.AddActivity


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habitance.R
import com.example.habitance.ui.theme.BackGround
import com.example.habitance.ui.theme.BackGround2
import com.example.habitance.ui.theme.Border
import com.example.habitance.ui.theme.BottomText
import com.example.habitance.ui.theme.TextDark
import com.example.habitance.ui.theme.fontFamily

@Composable
fun FormScreen() {
    var activity by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("") }
    var target by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGround)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "back button",
                    modifier = Modifier.size(25.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = (-30).dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier.size(72.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp, Border),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {

            Column(
                modifier = Modifier
                    .width(378.dp)
                    .height(720.dp)
                    .background(BackGround2)
                    .padding(vertical = 24.dp, horizontal = 24.dp)
            ) {

                Text(
                    text = "ADD ACTIVITY",
                    fontSize = 26.sp,
                    fontFamily = fontFamily,
                    color = TextDark,
                    fontWeight = FontWeight(600)
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextFieldActivity("Nama Aktivitas", activity, onValueChange = {activity = it})

                CategorySelection()

                Spacer(modifier = Modifier.height(16.dp))

                TextFieldActivity("Satuan", unit, onValueChange = {unit = it})

                TextFieldActivity("Target", target, onValueChange = {target = it})

                DatePicker()

                Spacer(modifier = Modifier.height(32.dp))

                Button(modifier = Modifier
                    .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(TextDark),
                    onClick = {}) {
                    Text(text = "Simpan", modifier = Modifier.padding(vertical = 3.dp, horizontal = 16.dp),
                        fontFamily = fontFamily, fontSize = 14.sp, fontWeight = FontWeight(400))
                }

            }
        }

    }
}

@Preview
@Composable
fun FormScreenPreview(){
    FormScreen()
}

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

@Composable
fun CategorySelection() {
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
            onClick = { category = "Baik" },
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
            onClick = { category = "Buruk" },
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

@Preview (showBackground = true)
@Composable
fun DatePickerPreview(){
    DatePicker()
}