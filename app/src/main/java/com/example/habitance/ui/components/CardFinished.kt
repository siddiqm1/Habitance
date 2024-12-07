package com.example.habitance.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.habitance.R
import com.example.habitance.ui.theme.BackGround
import com.example.habitance.ui.theme.BackGround2
import com.example.habitance.ui.theme.TextDark
import com.example.habitance.ui.theme.TextMedium
import com.example.habitance.ui.theme.fontFamily

@Composable
fun CardFinished() {
    Card(
        colors = CardDefaults.cardColors(BackGround2),
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp), // Bentuk sudut card
        elevation = CardDefaults.cardElevation(6.dp)
    ){
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .background(TextMedium)
            .padding(16.dp, 3.dp)
        ) {
            val (judulText, editIcon) = createRefs()
            Text(
                text = "Sugar",
                fontSize = 13.sp,
                fontWeight = FontWeight(500),
                fontFamily = fontFamily,
                color = TextDark,
                modifier = Modifier
                    .constrainAs(judulText){
                        start.linkTo(parent.start)
                        top.linkTo(parent.top, margin = 7.dp)
                        bottom.linkTo(parent.bottom, margin = 7.dp)
                    }
            )

            Icon(
                painter = painterResource(id=R.drawable.delete),
                contentDescription = "edit",
                tint = TextDark,
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
            val (targetIcon, targetText, startIcon, startText, endIcon, endText, percentText, noteButton, notePreview, totalWaktuText, targetTercapaiText) = createRefs()
            Icon(
                painter = painterResource(id = R.drawable.target),
                contentDescription = "target icon",
                tint = TextDark,
                modifier = Modifier
                    .size(25.dp)
                    .constrainAs(targetIcon){
                        start.linkTo(parent.start, margin = 13.dp)
                        end.linkTo(targetText.start)
                        top.linkTo(parent.top, margin = 8.dp)
                    }
            )
            Text(
                text = "0 kali / day",
                fontSize = 11.sp,
                fontFamily = fontFamily,
                color = TextDark,
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
                tint = TextDark,
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
                color = TextDark,
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
                tint = TextDark,
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
                color = TextDark,
                modifier = Modifier
                    .constrainAs(endText){
                        start.linkTo(endIcon.end, margin = 7.dp)
                        top.linkTo(endIcon.top)
                        bottom.linkTo(endIcon.bottom)
                    }
            )
            Text(
                text = "66.6 %",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(900),
                    color = Color(0xFF16423C)
                ),
                modifier = Modifier
                    .constrainAs(percentText){
                        end.linkTo(parent.end, margin = 10.dp)
                        top.linkTo(targetIcon.top)
                    }
            )
            Text(
                text = "Total waktu: 60 hari",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF16423C),
                ),
                modifier = Modifier
                    .constrainAs(totalWaktuText){
                        end.linkTo(percentText.end)
                        top.linkTo(percentText.bottom, margin = 5.dp)
                    }
            )
            Text(
                text = "Target tercapai: 40 hari",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF16423C),
                ),
                modifier = Modifier
                    .constrainAs(targetTercapaiText){
                        end.linkTo(totalWaktuText.end)
                        top.linkTo(totalWaktuText.bottom)
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
                    TextDark // Warna latar belakang tombol
                ),
                contentPadding = PaddingValues(0.dp) // Hilangkan padding default
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_paper),
                    contentDescription = "add note"
                )
            }
            Box(modifier = Modifier
                .background(color = TextMedium, shape = RoundedCornerShape(16.dp))
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
                    color = TextDark,
                    fontSize = 7.sp
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Preview
@Composable
fun CardListPreview() {
    CardFinished()
}