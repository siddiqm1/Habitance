package com.example.habitance

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habitance.ui.theme.BackGround
import com.example.habitance.ui.theme.BackGround2

val fontFamily = FontFamily(
    Font(R.font.lexend_bold, FontWeight.Bold),
    Font(R.font.lexend_thin, FontWeight.Thin),
    Font(R.font.lexend_black, FontWeight.Black),
    Font(R.font.lexend_light, FontWeight.Light),
    Font(R.font.lexend_medium, FontWeight.Medium),
    Font(R.font.lexend_extrabold, FontWeight.ExtraBold),
    Font(R.font.lexend_extralight, FontWeight.ExtraLight),
    Font(R.font.lexend_regular, FontWeight.Normal),
    Font(R.font.lexend_semibold, FontWeight.SemiBold),
)
@Composable
fun AddActivityScreen(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(BackGround)
            .padding(vertical = 10.dp)
    )
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start // Mengatur logo di tengah
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

            Box(modifier = Modifier
                .fillMaxWidth()
                .offset(x = (-30).dp),
                contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier.size(80.dp)
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(5.dp)

        ) {
            Column(
                modifier = Modifier
                    .width(370.dp)
                    .height(770.dp)
                    .background(BackGround2)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                // Header
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, start = 20.dp)
                ) {
                    Text(
                        text = "ACTIVITY LIST",
                        fontSize = 24.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(600),
                    )
                }

                // Image Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp) // Add top padding to create spacing between boxes
                        .height(250.dp), // Set a fixed height for the image box
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.folder),
                        contentDescription = "Folder",
                        modifier = Modifier.size(250.dp)
                    )
                }

                // No Activity Text Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                ) {
                    Text(
                        text = "Oops, belum ada aktivitas. Waktunya menambahkan yang pertama!",
                        fontSize = 18.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(600),
                        modifier = Modifier
                            .padding(start = 30.dp, end = 12.dp)
                    )
                }

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                ){
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(horizontal = 30.dp, vertical = 18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF16423C)
                        )
                    ) {
                        Text(
                            text = "ADD ACTIVITY",
                            fontSize = 16.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight(400),
                            modifier = Modifier
                                .padding(vertical = 6.dp, horizontal = 8.dp)
                        )
                    }
                }
            }

        }

    }
}

@Composable
@Preview
fun AddActivityScreenPreview(){
    AddActivityScreen()
}