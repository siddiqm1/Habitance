package com.example.habitance.screen.activity

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.habitance.R
import com.example.habitance.ui.theme.BackGround
import com.example.habitance.ui.theme.BackGround2
import com.example.habitance.ui.theme.Border
import com.example.habitance.ui.theme.NewGreen
import com.example.habitance.ui.theme.fontFamily


@Composable
fun CardList() {
    Card(
        colors = CardDefaults.cardColors(BackGround2),
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp), // Bentuk sudut card
        elevation = CardDefaults.cardElevation(6.dp)
    ){
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .background(Border)
            .padding(16.dp, 3.dp)
        ) {
            val (judulText, targetText, targetText2, editIcon) = createRefs()
            Text(
                text = "Membaca buku",
                fontSize = 13.sp,
                fontWeight = FontWeight(500),
                fontFamily = fontFamily,
                color = NewGreen,
                modifier = Modifier
                    .constrainAs(judulText){
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )
            Text(
                text = "TARGET",
                letterSpacing = 1.sp,
                fontSize = 6.sp,
                fontFamily = fontFamily,
                color = NewGreen,
                modifier = Modifier
                    .constrainAs(targetText){
                        end.linkTo(targetText2.end)
                        bottom.linkTo(targetText2.top)
                        top.linkTo(parent.top)
                    }
            )
            Text(
                text = "35/30",
                fontSize = 15.sp,
                fontFamily = fontFamily,
                color = NewGreen,
                fontWeight = FontWeight(900),
                modifier = Modifier
                    .constrainAs(targetText2){
                        end.linkTo(editIcon.start, margin = 24.dp)
                        top.linkTo(targetText.bottom)
                        bottom.linkTo(parent.bottom)
                    }
            )
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "edit",
                tint = NewGreen,
                modifier = Modifier
                    .size(17.dp)
                    .constrainAs(editIcon){
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
        ConstraintLayout(modifier = Modifier
            .background(BackGround)
            .fillMaxWidth()
        ){
            val (targetIcon, targetText, startIcon, startText, endIcon, endText, fireIcon, noteButton, notePreview) = createRefs()
            Icon(
                painter = painterResource(id = R.drawable.target),
                contentDescription = "target icon",
                tint = NewGreen,
                modifier = Modifier
                    .size(25.dp)
                    .constrainAs(targetIcon){
                        start.linkTo(parent.start, margin = 13.dp)
                        end.linkTo(targetText.start)
                        top.linkTo(parent.top, margin = 8.dp)
                    }
            )
            Text(
                text = "30 halaman / day",
                fontSize = 11.sp,
                fontFamily = fontFamily,
                color = NewGreen,
                modifier = Modifier
                    .constrainAs(targetText){
                        start.linkTo(targetIcon.end, margin = 5.dp)
                        top.linkTo(targetIcon.top)
                        bottom.linkTo(targetIcon.bottom)
                    }
            )
            Icon(
                painter = painterResource(id = R.drawable.flag),
                contentDescription = "start icon",
                tint = NewGreen,
                modifier = Modifier
                    .size(13.dp)
                    .constrainAs(startIcon){
                        start.linkTo(targetIcon.start, margin = 23.dp)
                        top.linkTo(targetIcon.bottom, margin = 3.dp)
                    }
            )
            Text(
                text = "03/01/2024",
                fontSize = 7.sp,
                fontFamily = fontFamily,
                color = NewGreen,
                modifier = Modifier
                    .constrainAs(startText){
                        start.linkTo(startIcon.end, margin = 7.dp)
                        top.linkTo(startIcon.top)
                        bottom.linkTo(startIcon.bottom)
                    }
            )
            Icon(
                painter = painterResource(R.drawable.flag2),
                contentDescription = "end icon",
                tint = NewGreen,
                modifier = Modifier
                    .size(13.dp)
                    .constrainAs(endIcon){
                        start.linkTo(startIcon.start)
                        top.linkTo(startIcon.bottom)
                    }
            )
            Text(
                text = "03/01/2024",
                fontSize = 7.sp,
                fontFamily = fontFamily,
                color = NewGreen,
                modifier = Modifier
                    .constrainAs(endText){
                        start.linkTo(endIcon.end, margin = 7.dp)
                        top.linkTo(endIcon.top)
                        bottom.linkTo(endIcon.bottom)
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.fire_on),
                contentDescription = "fire on",
                modifier = Modifier
                    .size(30.dp)
                    .constrainAs(fireIcon){
                        end.linkTo(parent.end, margin = 10.dp)
                        top.linkTo(targetIcon.top)
                    }
            )
            Button(
                onClick = {},
                modifier = Modifier
                    .size(35.dp) // Ukuran lingkaran
                    .clip(CircleShape) // Membuat bentuk lingkaran
                    .constrainAs(noteButton){
                        top.linkTo(endIcon.bottom, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 7.dp)
                        start.linkTo(parent.start, margin = 10.dp)
                    },
                colors = ButtonDefaults.buttonColors(
                    NewGreen // Warna latar belakang tombol
                ),
                contentPadding = PaddingValues(0.dp) // Hilangkan padding default
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_paper),
                    contentDescription = "add note"
                )
            }
            Box(modifier = Modifier
                .background(color = Border, shape = RoundedCornerShape(16.dp))
                .padding(10.dp, 8.dp)
                .constrainAs(notePreview){
                    start.linkTo(noteButton.end, margin = 6.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                    top.linkTo(noteButton.top)
                    bottom.linkTo(noteButton.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
            ){
                Text(
                    text = "lorem12",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(300),
                    color = NewGreen,
                    fontSize = 7.sp
                )
            }
        }
    }
}

//val activities = listOf(
//    ActivityItem(name = "Membaca Buku"),
//    ActivityItem(name = "Olahraga"),
//    ActivityItem(name = "Belajar Kotlin"),
//    // Tambahkan lebih banyak aktivitas sesuai kebutuhan
//)


//@Composable
//fun List(activities: List<ActivityItem>){
//    LazyColumn(
//        verticalArrangement = Arrangement.spacedBy(15.dp),
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        items(activities) { i ->
//            CardList(name = i.name) // Kirim data ke setiap item
//        }
//    }
//}

@Composable
fun ListActivity(){
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
                tint = NewGreen,
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
            )
        }
        Spacer(Modifier.size(15.dp))
        Card (
            colors = CardDefaults.cardColors(BackGround2),
            border = BorderStroke(1.dp, Border),
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
                    text= "ACTIVITY LIST",
                    fontSize = 22.sp,
                    color = NewGreen,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(600)
                )
                Spacer(modifier = Modifier.size(20.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .size(42.dp) // Ukuran lingkaran
                            .clip(CircleShape), // Membuat bentuk lingkaran
                        colors = ButtonDefaults.buttonColors(
                            NewGreen // Warna latar belakang tombol
                        ),
                        contentPadding = PaddingValues(0.dp) // Hilangkan padding default
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "plus",
                            tint = BackGround
                        )
                    }
                    Spacer(Modifier.size(12.dp))
                    OutlinedTextField(
                        value = "tes",
                        onValueChange = { },
                        label = { Text("Masukkan Nama") },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                Spacer(Modifier.size(21.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(BackGround)
                ){
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                color = NewGreen,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "Baik",
                            fontFamily = fontFamily,
                            color = BackGround,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(BackGround)
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "Buruk",
                            fontFamily = fontFamily,
                            color = NewGreen,
                        )
                    }
                }
                Spacer(Modifier.size(15.dp))

                CardList()
                CardList()
                CardList()
                CardList()

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CardList()
    ListActivity()
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview2() {
//    CardList()
//}