//package com.example.habitance
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import com.example.habitance.ui.screens.home.HomePage
//import com.example.habitance.ui.theme.BackGround
//import com.example.habitance.ui.theme.TextDark
//import com.example.habitance.ui.theme.TextLight
//import com.example.habitance.ui.theme.fontFamily
//
//@Composable
//fun BaikBuruk(){
//    Row(
//        horizontalArrangement = Arrangement.SpaceEvenly,
//        modifier = Modifier
//            .fillMaxWidth()
//            .clip(RoundedCornerShape(20.dp))
//    ) {
//        var isClickBaik by remember { mutableStateOf(true) }
//        var isClickBuruk by remember { mutableStateOf(false) }
//        Button(
//            onClick = {
//                isClickBaik = true
//                isClickBuruk = false
//                selectedCategory.value = "Baik"
//                filteredActivities.value = activities.value.filter {
//                    it.category == selectedCategory.value
//                }
//            },
//            modifier = Modifier
//                .weight(1f)
//                .clip(RoundedCornerShape(20.dp)),
//            colors = if(isClickBaik) ButtonDefaults.buttonColors(TextDark) else ButtonDefaults.buttonColors(TextLight),
//            contentPadding = PaddingValues(10.dp)
//        ) {
//            Text(
//                text = "Baik",
//                color = if(isClickBaik) TextLight else TextDark,
//                fontFamily = fontFamily
//            )
//        }
//        Spacer(Modifier.width(10.dp))
//        Button(
//            onClick = {
//                isClickBaik = false
//                isClickBuruk = true
//                selectedCategory.value = "Buruk"
//                filteredActivities.value = activities.value.filter {
//                    it.category == selectedCategory.value
//                }
//            },
//            modifier = Modifier
//                .weight(1f)
//                .clip(RoundedCornerShape(20.dp)),
//            colors = if(isClickBuruk) ButtonDefaults.buttonColors(TextDark) else ButtonDefaults.buttonColors(TextLight),
//            contentPadding = PaddingValues(10.dp)
//        ) {
//            Text(
//                text = "Buruk",
//                color = if(isClickBuruk) TextLight else TextDark,
//                fontFamily = fontFamily
//            )
//        }
//    }
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun HomePreview(){
//    BaikBuruk()
//}