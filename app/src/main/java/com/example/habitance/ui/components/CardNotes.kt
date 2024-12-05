package com.example.habitance.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.habitance.ui.theme.TextMedium
import com.example.habitance.ui.theme.TextDark
import com.example.habitance.ui.theme.TextLight
import com.example.habitance.ui.theme.TextMedium
import com.example.habitance.ui.theme.fontFamily

@Composable
fun CardNotes() {
    Card(
        colors = CardDefaults.cardColors(BackGround2),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp), // Bentuk sudut card
        elevation = CardDefaults.cardElevation(6.dp)
    ){
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .background(TextMedium)
            .padding(16.dp, 3.dp)
            .height(35.dp)
        ) {
            val (judulText, editIcon) = createRefs()
            Text(
                text = "User's Note",
                fontSize = 15.sp,
                fontWeight = FontWeight(600),
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
                painter = painterResource(id= R.drawable.maximize),
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

        Box(
            modifier = Modifier
                .background(TextLight)
                .fillMaxWidth()
                .height(100.dp)
                .padding(11.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(TextLight, shape = RoundedCornerShape(8.dp))
                    .border(1.dp, TextMedium, shape = RoundedCornerShape(8.dp))
                    .fillMaxSize()
                    .padding(6.dp)
            ){
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi ornare ipsum in rutrum sollicitudin. Morbi purus nibh, sodales ac malesuada ac, semper eget elit. Pellentesque tincidunt dolor id posuere auctor. Praesent laoreet cursus mauris, a imperdiet lorem efficitur sed. Phasellus porta tempus felis, rutrum aliquet urna tincidunt in. Sed feugiat lorem ex, vel tempor enim ...",
                    style = TextStyle(
                        fontSize = 7.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(300),
                        color = Color(0xFF16423C),
                    )
                )
            }

        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}


@Preview
@Composable
fun CardNotesPreview() {
    CardNotes()
}