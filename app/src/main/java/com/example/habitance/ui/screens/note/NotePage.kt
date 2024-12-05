package com.example.habitance.ui.screens.note


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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import com.example.habitance.R
import com.example.habitance.navbar.Screen
import com.example.habitance.ui.theme.BackGround
import com.example.habitance.ui.theme.BackGround2
import com.example.habitance.ui.theme.Border
import com.example.habitance.ui.theme.Border2
import com.example.habitance.ui.theme.BottomText
import com.example.habitance.ui.theme.TextDark
import com.example.habitance.ui.theme.TextLogo
import com.example.habitance.ui.theme.fontFamily

@Composable
fun NotePage(navController: NavHostController) {
    var activity by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    Column(modifier = Modifier
        .fillMaxSize()
        .background(BackGround)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = { navController.navigate(Screen.HomeScreen.route)},
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

        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)) {

            Column(modifier = Modifier
                .width(378.dp)
                .height(720.dp)
                .background(BackGround2)
                .padding(vertical = 24.dp, horizontal = 24.dp)) {

                Text(
                    text = "NOTES",
                    fontSize = 26.sp,
                    fontFamily = fontFamily,
                    color = Border,
                    fontWeight = FontWeight(600)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Card(modifier = Modifier
                    .wrapContentSize(Alignment.Center)
                    .border(width = 2.dp, color = Border, shape = RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(5.dp)) {

                    Column(modifier = Modifier
                        .fillMaxWidth()) {

                        Column(modifier = Modifier
                            .background(Border)
                            .fillMaxWidth()
                            .padding(16.dp)){
                            TextField(
                                value = activity,
                                onValueChange = { activity = it },
                                label = {
                                    Text(
                                        text = "Masukkan Nama Activity",
                                        color = Border2,
                                        fontSize = 13.sp
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        width = 1.dp,
                                        color = Border2,
                                        shape = MaterialTheme.shapes.medium
                                    ),
                                shape = MaterialTheme.shapes.medium,
                                colors = TextFieldDefaults.colors().copy(
                                    focusedContainerColor = Border,
                                    unfocusedContainerColor = Border,
                                    cursorColor = Color.Black,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent
                                ),
                                textStyle = LocalTextStyle.current.copy(color = Color.Black)
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(BottomText)
                                .padding(16.dp)
                                .verticalScroll(scrollState)
                        ) {
                            TextField(
                                value = body,
                                onValueChange = { body = it },
                                label = {
                                    Text(
                                        text = "Masukkan Note",
                                        color = TextDark,
                                        fontSize = 13.sp
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        width = 1.dp,
                                        color = TextDark,
                                        shape = MaterialTheme.shapes.medium
                                    ),
                                shape = MaterialTheme.shapes.medium,
                                colors = TextFieldDefaults.colors().copy(
                                    focusedContainerColor = Border2,
                                    unfocusedContainerColor = Border2,
                                    cursorColor = Color.Black,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent
                                ),
                                textStyle = LocalTextStyle.current.copy(color = Color.Black)
                            )


                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                modifier = Modifier
                                    .align(Alignment.End), // Menempatkan tombol di sisi kanan
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = TextDark,
                                    contentColor = BackGround2
                                )
                            ) {
                                Text(text = "Simpan")
                            }
                        }


                    }

                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotePagePreview() {
    NotePage(navController = rememberNavController())
}


