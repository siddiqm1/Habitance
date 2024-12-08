package com.example.habitance.ui.screens.finishedactivity

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.habitance.R
import com.example.habitance.navbar.Screen
import com.example.habitance.ui.components.CardFinished
import com.example.habitance.ui.theme.BackGround
import com.example.habitance.ui.theme.BackGround2
import com.example.habitance.ui.theme.TextDark
import com.example.habitance.ui.theme.TextLight
import com.example.habitance.ui.theme.TextMedium
import com.example.habitance.ui.theme.fontFamily

@Composable
fun FinishedActivity(navController: NavHostController){
    val searchQuery = remember { mutableStateOf("") }

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
            elevation = CardDefaults.cardElevation(8.dp), // Untuk bayangan card
            shape = RoundedCornerShape(16.dp) // Bentuk sudut card
        ) {
            Column(
                modifier = Modifier.padding(25.dp)
            ){
                Text(
                    text= "FINISHED ACTIVITY",
                    fontSize = 22.sp,
                    color = TextDark,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(600)
                )
                Spacer(modifier = Modifier.size(20.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Button(
                        onClick = {navController.navigate(Screen.AddActivityScreen.route)},
                        modifier = Modifier
                            .size(42.dp) // Ukuran lingkaran
                            .clip(CircleShape), // Membuat bentuk lingkaran
                        colors = ButtonDefaults.buttonColors(
                            TextDark // Warna latar belakang tombol
                        ),
                        contentPadding = PaddingValues(0.dp) // Hilangkan padding default
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "plus",
                            tint = BackGround2
                        )
                    }
                    Spacer(Modifier.size(12.dp))
                    BasicTextField(
                        value = searchQuery.value,
                        onValueChange = { query ->
                            searchQuery.value = query
//                            filteredActivities.value = activities.value.filter {
//                                it.name.contains(query, ignoreCase = true)
//                            }
                        },
                        modifier = Modifier
                            .background(TextLight, shape = RoundedCornerShape(size = 20.dp))
                            .border(width = 1.dp, color = TextDark, shape = RoundedCornerShape(size = 20.dp))
                            .height(42.dp)
                            .fillMaxWidth(),
                        decorationBox = { innerTextField ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search Icon",
                                    tint = TextDark,
                                    modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                                )
                                Box(
                                    Modifier.weight(1f),
                                    contentAlignment = Alignment.CenterStart
                                ){
                                    if(searchQuery.value.isEmpty()){
                                        Text(
                                            text = "Search",
                                            fontSize = 13.sp,
                                            color = TextDark.copy(alpha = 0.5f),
                                            fontFamily = fontFamily
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        }
                    )
                }
                Spacer(Modifier.size(21.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                ) {
                    var isClickBaik by remember { mutableStateOf(true) }
                    var isClickBuruk by remember { mutableStateOf(false) }
                    Button(
                        onClick = {
                            isClickBaik = true
                            isClickBuruk = false
//                            selectedCategory.value = "Baik"
//                            filteredActivities.value = activities.value.filter {
//                                it.category == selectedCategory.value
//                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(20.dp)),
                        colors = if(isClickBaik) ButtonDefaults.buttonColors(TextDark) else ButtonDefaults.buttonColors(
                            TextLight
                        ),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        Text(
                            text = "Baik",
                            color = if(isClickBaik) TextLight else TextDark,
                            fontFamily = fontFamily
                        )
                    }
                    Spacer(Modifier.width(10.dp))
                    Button(
                        onClick = {
                            isClickBaik = false
                            isClickBuruk = true
//                            selectedCategory.value = "Buruk"
//                            filteredActivities.value = activities.value.filter {
//                                it.category == selectedCategory.value
//                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(20.dp)),
                        colors = if(isClickBuruk) ButtonDefaults.buttonColors(TextDark) else ButtonDefaults.buttonColors(
                            TextLight
                        ),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        Text(
                            text = "Buruk",
                            color = if(isClickBuruk) TextLight else TextDark,
                            fontFamily = fontFamily
                        )
                    }
                }
                Spacer(Modifier.size(15.dp))

                CardFinished()
                CardFinished()
                CardFinished()
                CardFinished()
                CardFinished()
                CardFinished()
            }
        }
    }
}

@Preview
@Composable
fun ActivityListPreview(){
    FinishedActivity(navController = rememberNavController())
}