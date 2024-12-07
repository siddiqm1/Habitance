package com.example.habitance.ui.screens.detailactivity

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.habitance.R
import com.example.habitance.navbar.Screen
import com.example.habitance.ui.theme.BackGround
import com.example.habitance.ui.theme.BackGround2
import com.example.habitance.ui.theme.TextDark
import com.example.habitance.ui.theme.TextLight
import com.example.habitance.ui.theme.TextMedium
import com.example.habitance.ui.theme.fontFamily

@Composable
fun ActivityList(navController: NavHostController){

    var yes by remember {mutableStateOf(true)}

    Column(modifier = Modifier
        .fillMaxSize()
        .background(BackGround)
        .padding(0.dp, 15.dp)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back icon",
                tint = TextDark,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 32.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(61.dp)
                    .height(51.dp)
                    .align(Alignment.Center)
                    .clickable { navController.navigate(Screen.HomeScreen.route) }
            )
        }
        Spacer(Modifier.size(15.dp))
        Card (
            colors = CardDefaults.cardColors(BackGround2),
            border = BorderStroke(1.dp, TextMedium),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp, 0.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            shape = RoundedCornerShape(16.dp)
        ) {

//            ISI DI DALAM CARD BESAR

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
            ){
                Text(
                    text= "Membaca Buku",
                    fontSize = 22.sp,
                    color = TextDark,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(600)
                )
                Spacer(Modifier.size(18.dp))

//                KALENDER
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                        }
                ){
                    Row(
                        modifier = Modifier
                            .background(TextMedium, shape = RoundedCornerShape(30.dp))
                            .clip(RoundedCornerShape(30.dp))
                            .padding(15.dp, 12.dp)
                    ) {
                        Text(
                            "Rabu, 03 Maret 2004",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight(500),
                                color = TextDark,
                            )
                        )
                        Spacer(Modifier.size(5.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.kalender),
                            null
                        )
                    }
                }
                Spacer(Modifier.size(26.dp))
                Text("Apakah Anda melakukannya?",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(600),
                        color = TextDark,
                    )
                )
                Spacer(Modifier.size(12.dp))

                Row() {
                    Text(
                        "Ya",
                        fontFamily = fontFamily,
                        color = if (yes) TextDark else TextLight,
                        modifier = Modifier
                            .background(
                                if (yes) TextLight else TextDark,
                                shape = RoundedCornerShape(30.dp)
                            )
                            .clickable { yes = false }
                            .padding(22.dp, 6.dp)
                    )
                    Spacer(Modifier.size(10.dp))
                    Text(
                        "Tidak",
                        fontFamily = fontFamily,
                        color = if (!yes) TextDark else TextLight,
                        modifier = Modifier
                            .background(
                                if (!yes) TextLight else TextDark,
                                shape = RoundedCornerShape(30.dp)
                            )
                            .clickable {
                                yes = true

                            }
                            .padding(22.dp, 6.dp)
                    )
                }


                if (!yes){
                    Spacer(Modifier.size(31.dp))
                    Text(
                        "Intensitas:",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight(500)),
                            color = TextDark
                    )
                    Spacer(Modifier.size(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = "",
                            onValueChange = {  },
                            modifier = Modifier
                                .height(25.dp)
                                .width(53.dp)
                                .background(color = TextLight, shape = RoundedCornerShape(20.dp))
                                .clip(RoundedCornerShape(20.dp)), // Bentuk sudut
                            shape = RoundedCornerShape(20.dp), // Bentuk sudut TextField
                            textStyle = TextStyle(
                                fontFamily = fontFamily,       // Font custom (jika diperlukan)
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = TextDark
                            )
                        )
                        Spacer(Modifier.size(6.dp))
                        Text(
                            text = "halaman",
                            style = TextStyle(
                                fontSize = 9.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight(400),
                                color = TextDark,
                            )
                        )
                    }
                }

                Spacer(Modifier.size(30.dp))
                Text(
                    "Simpan",
                    color = TextLight,
                    fontFamily = fontFamily,
                    modifier = Modifier
                        .background(TextDark, shape = RoundedCornerShape(30.dp))
                        .clip(RoundedCornerShape(20.dp))
                        .padding(22.dp, 6.dp)
                        .clickable {

                        }
                )
                }
            }
        }
    }


@Preview
@Composable
fun ActivityListPreview(){
    ActivityList(navController = rememberNavController())
}
