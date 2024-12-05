package com.example.habitance.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.habitance.R
import com.example.habitance.function.AuthManager
import com.example.habitance.navbar.BottomBarScreen
import com.example.habitance.navbar.Screen
import com.example.habitance.ui.theme.BackGround2
import com.example.habitance.ui.theme.TextDark
import com.example.habitance.ui.theme.fontFamily
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun HomePage(navController: NavController,navMainController: NavController,) {
    val context = LocalContext.current
    val photoProfile = Firebase.auth.currentUser?.photoUrl.toString()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE9EFEC))
    ) {
        //head
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(157.dp)
                .background(Color(0xFF6A9C89))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(28.dp,20.dp),
                verticalAlignment = Alignment.CenterVertically, // Agar elemen berada di tengah secara vertikal
                horizontalArrangement = Arrangement.SpaceBetween // Icon logout berada di ujung kanan
            ) {
                // Foto Profil
                if (photoProfile.isNotEmpty()) {
                    AsyncImage(
                        model = photoProfile,
                        contentDescription = "Foto Profil",
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(Color.Gray, CircleShape)
                            .clickable {
                                navController.navigate(route = Screen.ProfileScreen.route)// Ganti dengan route halaman profil
                            }
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                            .clickable {
                                navController.navigate(route = Screen.ProfileScreen.route) // Ganti dengan route halaman profil
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "N/A",
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }

                // Teks
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "Hello,",
                        color = Color(0xFF16423C),
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Default
                    )
                    Text(
                        text = "Barnibar",
                        color = Color(0xFF16423C),
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold
                    )
                }


                // Icon Keluar
                Icon(

                    painter = painterResource(id = R.drawable.logout),
                    contentDescription = "Keluar",
                    modifier = Modifier.size(24.dp)
                        .clickable {
                        AuthManager(context).signOut()
                        navMainController.navigate("login"){
                            popUpTo("home") { inclusive = true }
                        }

                    },
                    tint = TextDark
                )
            }
        }


        Column(){

        }
        // "What did you do today?"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-44).dp)
                .padding(horizontal = 21.dp)
                .height(88.dp)
                .shadow(4.dp, shape = RoundedCornerShape(20.dp))
                .background(Color(0xFF16423C))
                .clickable { navController.navigate(Screen.AddActivityScreen.route) }
        ) {
            // Icon Note
            Image(
                painter = painterResource(id = R.drawable.what_do_you_do_icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(18.dp,0.dp)
                    .size(48.dp)
                    .align(Alignment.CenterStart)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Teks "What did you do today?"
            Column(
                modifier = Modifier
                    .padding(90.dp,0.dp,0.dp,0.dp)
                    .align(Alignment.CenterStart)
            ) {
                Text(
                    text = "What did you do today?",
                    fontSize = 18.sp,
                    color = Color(0xFFC4DAD2),
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.size(4.dp))
                Text(
                    text = "Catat aktivitas yang sudah Anda lakukan!",
                    fontSize = 9.sp,
                    color = Color(0xFFC4DAD2),
                    fontFamily = FontFamily.Default
                )
            }
        }

        //tanggal
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .offset(y=-154.dp)
                .border(width = 2.dp, color = Color(0xFF16423C), shape = RoundedCornerShape(size = 15.dp))
                .width(146.dp)
                .height(35.dp)
                .background(color = BackGround2, shape = RoundedCornerShape(15.dp))
        ){
            Text(
                "Rabu, 03 Maret 2024",
                fontFamily = fontFamily,
                fontSize = 11.sp,
                color = TextDark,
                fontWeight = FontWeight(700)
            )
        }

        // Logo
        Icon(
            painter = painterResource(id = R.drawable.logo), // Replace with your logo resource
            contentDescription = "Logo",
            modifier = Modifier
                .offset(y = (-80).dp)
                .size(80.dp),
            tint = Color(0xFF16AC86)
        )

        //MENU-MENU
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .offset(y=-80.dp)
                .padding(21.dp)
        ) {
            val (activityList, streaks, lightMode, finishedActivity, notes) = createRefs()

            // Kotak Activity List
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(125.dp)
                    .shadow(3.dp, shape = RoundedCornerShape(12.dp))
                    .background(Color(0xFFC4DAD2))
                    .constrainAs(activityList){
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
            ) {
                Text(
                    text = "Activity List",
                    fontSize = 22.sp,
                    color = Color(0xFF16423C),
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(24.dp,22.dp,0.dp,0.dp)

                )

                // Bagian kanan: Icon Notifikasi
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(18.dp,12.dp)
                ){
                    Text(
                        text = "Set your regular reminder!",
                        fontSize = 14.sp,
                        color = Color(0xFF16423C),
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(300)
                    )
                    Spacer(Modifier.size(16.dp))
                    Box(
                        modifier = Modifier
                            .size(55.dp)
                            .background(Color(0xFF16423C), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.notif), // Pastikan file notif ada di drawable
                            contentDescription = "Notification Icon",
                            tint = Color(0xFFC4DAD2), // Warna ikon
                            modifier = Modifier
                                .size(26.dp)
                                .clickable { navController.navigate(Screen.NotificationScreen.route) }
                        )
                    }
                }
            }

            // Progress Bar untuk Streaks
            Box(
                modifier = Modifier
                    .constrainAs(streaks){
                        start.linkTo(parent.start)
                        top.linkTo(activityList.bottom, margin = 15.dp)
                        bottom.linkTo(finishedActivity.top)
                    }
            ) {
                Column() {
                    Text(
                        text = "Keep up your STREAKS!",
                        color = Color(0xFF16423C),
                        fontSize = 12.sp,
                        fontWeight = FontWeight(500),
                        letterSpacing = -(0.75).sp,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.height(23.dp))

                    // Progress Bar
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .width(200.dp)
                                .height(12.dp)
                                .background(Color.Gray, shape = RoundedCornerShape(6.dp))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(140.dp) // Ubah untuk persentase
                                    .background(Color(0xFF16423C), shape = RoundedCornerShape(6.dp))
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.fire), // Icon berbentuk api
                            contentDescription = "Streak",
                            tint = Color(0xFFFFA726), // Warna oranye untuk ikon
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(Modifier.size(15.dp))
                    Text(
                        text = "7 / 10",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF16423C),
                        letterSpacing = 1.sp
                    )
                }
            }

            // Light Mode
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .background(Color(0xFF6A9C89), shape = RoundedCornerShape(16.dp))
                    .padding(16.dp)
                    .constrainAs(lightMode){
                        bottom.linkTo(notes.top)
                        top.linkTo(streaks.top)
                        end.linkTo(parent.end)
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = R.drawable.light_mode), // Ikon Light Mode
                        contentDescription = "Light mode",
                        tint = Color(0xFFE9EFEC),
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Light Mode",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE9EFEC)

                    )
                }
            }

            // Finished Activity
            Box(
                modifier = Modifier
                    .background(Color(0xFFC4DAD2), shape = RoundedCornerShape(16.dp))
                    .clickable { navController.navigate(Screen.FinishedActivityEmpty.route) }
                    .constrainAs(finishedActivity){
                        start.linkTo(parent.start)
                        end.linkTo(streaks.end)
                        top.linkTo(streaks.bottom, margin = 15.dp)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = R.drawable.finish), // Ikon Finished Activity
                        contentDescription = "Finished Activity",
                        tint = Color(0xFF16423C),
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Finished Activity",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF16423C)
                    )
                }
            }

            // Notes
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .background(Color(0xFFC4DAD2), shape = RoundedCornerShape(16.dp))
                    .padding(16.dp)
                    .clickable { navController.navigate(Screen.NoteScreen.route) }
                    .constrainAs(notes){
                        bottom.linkTo(finishedActivity.bottom)
                        top.linkTo(lightMode.bottom)
                        end.linkTo(parent.end)
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = R.drawable.note), // Ikon Notes
                        contentDescription = "Notes",
                        tint = Color(0xFF16423C),
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Notes",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF16423C)
                    )
                }
            }

        }
    }
}







