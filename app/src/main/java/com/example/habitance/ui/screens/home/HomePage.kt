package com.example.habitance.ui.screens.home

import android.util.Log
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.habitance.R
import com.example.habitance.function.AuthManager
import com.example.habitance.navbar.BottomBarScreen
import com.example.habitance.navbar.Screen
import com.example.habitance.ui.screens.activity_list.fetchActivities
import com.example.habitance.ui.screens.add_activity.Activity
import com.example.habitance.ui.screens.add_activity.CategoryActivity
import com.example.habitance.ui.screens.detailactivity.showExtendedDate
import com.example.habitance.ui.screens.profile.ProfileViewModel
import com.example.habitance.ui.theme.BackGround2
import com.example.habitance.ui.theme.TextDark
import com.example.habitance.ui.theme.fontFamily
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

@Composable
fun HomePage(
    navController: NavController,
    navMainController: NavController,
    profileViewModel: ProfileViewModel = viewModel(),
) {
    val context = LocalContext.current
    val photoProfile = Firebase.auth.currentUser?.photoUrl.toString()
    val name = profileViewModel.name.collectAsState().value
    val currentDate = Timestamp.now()
    val activities = remember { mutableStateOf<List<Activity>>(emptyList()) }
    var streakCount by remember {
        mutableIntStateOf(
            0
        )
    }

    LaunchedEffect(Unit) {
        fetchActivities(
            onResult = { activity ->
                val filtered = activity.filter {
                    it.end.seconds > Timestamp.now().seconds && it.start.seconds < Timestamp.now().seconds
                }
                Log.d("fetchActivitiesResult", "Fetched activities: $filtered")
                activities.value = filtered
            },
            onError = {
                emptyList<Activity>()
            }
        )
    }

    LaunchedEffect(activities.value) {
        Log.d("HomePageStreaks", "Current Progress: ${activities.value}")

        streakCount = activities.value.count {
            val diffInMillis = currentDate.seconds - it.start.seconds
            val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis).toInt()
            val currentProgress = it.progress[diffInDays.toString()] ?: 0
            if (it.category == CategoryActivity.Baik) {
                it.target <= currentProgress
            } else {
                currentProgress >= 0
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE9EFEC))
    ) {
        Box {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(145.dp)
                    .background(Color(0xFF6A9C89))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(28.dp, 20.dp),
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
                            text = name,
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
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                AuthManager(context).signOut()
                                navMainController.navigate("login") {
                                    popUpTo(0) {
                                        inclusive = true
                                    } // Menghapus semua layar sebelumnya
                                }
                            },
                        tint = TextDark
                    )

                }
            }

            Box(
                modifier = Modifier
                    .offset(y = 42.dp)
                    .align(Alignment.BottomCenter)
            ) {
                // "What did you do today?"
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        //                    .offset(y = (-44).dp)
                        .padding(horizontal = 21.dp)
                        .height(88.dp)
                        .shadow(4.dp, shape = RoundedCornerShape(20.dp))
                        .background(Color(0xFF16423C))
                        .align(Alignment.BottomCenter)
                        .clickable { navController.navigate(Screen.ActivityListEmpty.route) }
                ) {
                    // Icon Note
                    Image(
                        painter = painterResource(id = R.drawable.what_do_you_do_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(18.dp, 0.dp)
                            .size(48.dp)
                            .align(Alignment.CenterStart)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    // Teks "What did you do today?"
                    Column(
                        modifier = Modifier
                            .padding(90.dp, 0.dp, 0.dp, 0.dp)
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
                        .offset(y = (-71).dp)
                        .border(
                            width = 2.dp,
                            color = Color(0xFF16423C),
                            shape = RoundedCornerShape(size = 15.dp)
                        )
                        .width(146.dp)
                        .height(35.dp)
                        .align(Alignment.BottomCenter)
                        .background(color = BackGround2, shape = RoundedCornerShape(15.dp))
                ) {
                    Text(
                        text = currentDate.showExtendedDate(),
                        fontFamily = fontFamily,
                        fontSize = 10.sp,
                        color = TextDark,
                        fontWeight = FontWeight(700),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.size(35.dp))
            // Logo
            Icon(
                painter = painterResource(id = R.drawable.logo), // Replace with your logo resource
                contentDescription = "Logo",
                modifier = Modifier
                    .padding(10.dp)
                    .size(60.dp),
                tint = Color(0xFF16AC86)
            )

            //MENU-MENU
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(21.dp, 0.dp, 21.dp, 21.dp)
            ) {
                // Kotak Activity List
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(125.dp)
                        .shadow(3.dp, shape = RoundedCornerShape(12.dp))
                        .background(Color(0xFFC4DAD2))
                ) {
                    Text(
                        text = "Activity List",
                        fontSize = 22.sp,
                        color = Color(0xFF16423C),
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(24.dp, 22.dp, 0.dp, 0.dp)

                    )

                    // Bagian kanan: Icon Notifikasi
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(18.dp, 12.dp)
                    ) {
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
                                    .clickable {
                                        navController.navigate(Screen.NotificationScreen.route)
                                    }
                            )
                        }
                    }
                }

                Spacer(Modifier.size(22.dp))

                // Progress Bar untuk Streaks
                Streaks(streakCount, activities.value.size)

                Spacer(Modifier.size(15.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    // Finished Activity
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f) // Sama weight dengan Notes
                            .background(Color(0xFFC4DAD2), shape = RoundedCornerShape(16.dp))
                            .clickable {
                                navController.navigate(BottomBarScreen.FinishedActivity.route)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = painterResource(id = R.drawable.finish), // Ikon Finished Activity
                                contentDescription = "Finished Activity",
                                tint = Color(0xFF16423C),
                                modifier = Modifier.size(60.dp)
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

                    Spacer(Modifier.size(22.dp))

                    // Notes
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f) // Sama weight dengan Finished Activity
                            .background(Color(0xFFC4DAD2), shape = RoundedCornerShape(16.dp))
                            .clickable {
                                navController.navigate(BottomBarScreen.Note.route)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = painterResource(id = R.drawable.note), // Ikon Notes
                                contentDescription = "Notes",
                                tint = Color(0xFF16423C),
                                modifier = Modifier.size(60.dp)
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
    }
}


                @Composable
fun Streaks(
    success: Int,
    total: Int
){
    val percent = success.toFloat() / total.toFloat()
    val percentText = if(percent.isNaN()) 0 else percent * 100

    Column {
        Text(
            text = buildAnnotatedString {
                append("Keep up your ")
                withStyle(style = SpanStyle(fontWeight = FontWeight(900), fontSize = 14.sp, color = Color(0xFF16423C))) {
                    append(" STREAKS!")
                }
            },
            color = Color(0xFF16423C),
            fontSize = 12.sp,
            letterSpacing = -(0.75).sp,
            textAlign = TextAlign.Start
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(Color(0xFFC4DAD2), shape = RoundedCornerShape(15.dp))
            )

            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth(percent)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .background(Color(0xFF16423C), shape = RoundedCornerShape(15.dp))
                )
                Image(
                    painter = painterResource(id = R.drawable.fire_on), // Icon berbentuk api
                    contentDescription = "Streak",
                    modifier = Modifier
                        .size(41.dp)
                        .offset(y = (-5).dp, x = 5.dp)
                        .align(Alignment.CenterEnd)
                )
            }
        }
        Text(
            text = "$success / $total ($percentText %)",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
