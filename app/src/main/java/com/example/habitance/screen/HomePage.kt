package com.example.habitance.screen

import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.habitance.AuthManager
import com.example.habitance.R

@Composable
fun HomePage(
//    modifier: Modifier = Modifier,
//    authManager: NavHostController,
    navController: NavHostController
) {
    Column(
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
                .padding(horizontal = 21.dp)


        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically, // Agar elemen berada di tengah secara vertikal
                horizontalArrangement = Arrangement.SpaceBetween // Icon logout berada di ujung kanan
            ) {
                // Foto Profil
                Box(
                    modifier = Modifier
                        .size(59.dp)
                        .background(Color.Gray, shape = CircleShape) // Circle untuk foto profil
                )

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

                val context = LocalContext.current
                // Icon Keluar
                Icon(

                    painter = painterResource(id = R.drawable.logout),
                    contentDescription = "Keluar",
                    modifier = Modifier.size(24.dp).clickable {
                        AuthManager(context).signOut()
                        navController.navigate("login")

                    },
                    tint = Color.Black
                )
            }
        }


        // "What did you do today?"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-44).dp)
                .padding(horizontal = 21.dp)
                .height(88.dp)
                .shadow(4.dp, shape = RoundedCornerShape(12.dp))
                .background(Color(0xFF16423C))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                // Icon Note
                Icon(
                    painter = painterResource(id = R.drawable.notes),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = Color(0xFFC4DAD2)
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Teks "What did you do today?"
                Column {
                    Text(
                        text = "What did you do today?",
                        fontSize = 18.sp,
                        color = Color(0xFFC4DAD2),
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Catat aktivitas yang sudah Anda lakukan!",
                        fontSize = 9.sp,
                        color = Color(0xFFC4DAD2),
                        fontFamily = FontFamily.Default
                    )
                }
            }
        }


        // Tulisan Habitance dan Logo
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
                .offset(y = (-40).dp)
        ) {
            // Logo
            Icon(
                painter = painterResource(id = R.drawable.logo), // Replace with your logo resource
                contentDescription = "Logo",
                modifier = Modifier.size(60.dp),
                tint = Color.Unspecified
            )
            // Teks Habitance
            Text(
                text = "Habitance",
                fontSize = 20.sp,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF16AC86)
            )
        }


        // Kotak Activity List
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 21.dp)
                .height(125.dp)
                .offset(y = (-35).dp)
                .shadow(3.dp, shape = RoundedCornerShape(12.dp))
                .background(Color(0xFFC4DAD2))
        ) {
            // Text dan Icon Notifikasi dalam Row
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Bagian kiri: Text "Activity List"
                Column {
                    Text(
                        text = "Activity List",
                        fontSize = 22.sp,
                        color = Color(0xFF16423C),
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp)) // Spasi antar teks
                    Text(
                        text = "Set your regular reminder!",
                        fontSize = 14.sp,
                        color = Color(0xFF16423C),
                        fontFamily = FontFamily.Default
                    )
                }

                // Bagian kanan: Icon Notifikasi
                Box(
                    modifier = Modifier
                        .size(65.dp)
                        .background(Color(0xFF16423C), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.notif), // Pastikan file notif ada di drawable
                        contentDescription = "Notification Icon",
                        tint = Color(0xFFC4DAD2), // Warna ikon
                        modifier = Modifier.size(35.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        //test
        // Progress Bar untuk Streaks
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 21.dp, vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Keep up your STREAKS!",
                    color = Color(0xFF16423C),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

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

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "7 / 10",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF16423C)
                )
            }
        }

        //test
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp) // Jarak antar elemen
        ) {
            // Light Mode (Bagian atas)
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .align(Alignment.CenterHorizontally) // Posisikan di tengah
                    .background(Color(0xFF6A9C89), shape = RoundedCornerShape(16.dp))
                    .padding(16.dp),
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

            // Finished Activity & Notes (Bagian bawah)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween // Beri jarak antar elemen kiri & kanan
            ) {
                // Finished Activity (Kiri bawah)
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .background(Color(0xFFC4DAD2), shape = RoundedCornerShape(16.dp))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = R.drawable.finis), // Ikon Finished Activity
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

                // Notes (Kanan bawah)
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .background(Color(0xFFC4DAD2), shape = RoundedCornerShape(16.dp))
                        .padding(16.dp),
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
}


