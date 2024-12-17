package com.example.habitance.ui.components


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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
import com.example.habitance.ui.screens.add_activity.Activity
import com.example.habitance.ui.theme.BackGround
import com.example.habitance.ui.theme.BackGround2
import com.example.habitance.ui.theme.TextMedium
import com.example.habitance.ui.theme.TextDark
import com.example.habitance.ui.theme.TextLight
import com.example.habitance.ui.theme.TextMedium
import com.example.habitance.ui.theme.fontFamily
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun CardNoteUser() {
    var isOpened by remember { mutableStateOf(false) }
    var note by remember { mutableStateOf("") }
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
                    .clickable {
                        isOpened = !isOpened
                    }
            )
        }

        Box(
            modifier = Modifier
                .background(TextLight)
                .fillMaxWidth()
                .height(if (!isOpened) 100.dp else 800.dp)
                .padding(11.dp)
        ) {
            if (!isOpened) {
                Box(
                    modifier = Modifier
                        .background(TextLight, shape = RoundedCornerShape(8.dp))
                        .border(1.dp, TextMedium, shape = RoundedCornerShape(8.dp))
                        .fillMaxSize()
                        .padding(6.dp)
                ) {
                    if (note.isEmpty()) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "Write a note",
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight(300),
                                    color = Color(0xFF16423C),
                                )
                            )
                        }
                    } else {
                        Text(
                            text = note,
                            style = TextStyle(
                                fontSize = 8.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight(300),
                                color = Color(0xFF16423C),
                            )
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(6.dp),
//                    verticalArrangement = Arrangement.SpaceBetween // memastikan elemen diatur secara vertikal
                ) {
                    // TextField
                    BasicTextField(
                        value = note,
                        onValueChange = { query ->
                            note = query

                        },
                        modifier = Modifier
                            .background(TextLight, shape = RoundedCornerShape(8.dp))
                            .border(1.dp, TextMedium, shape = RoundedCornerShape(8.dp))
                            .fillMaxWidth()
                            .weight(1f) // memastikan TextField mengambil ruang yang cukup
                            .padding(6.dp)
                            .verticalScroll(rememberScrollState()),
                        decorationBox = { innerTextField ->
                            Box(
                                contentAlignment = if(note.isEmpty()) Alignment.Center else Alignment.TopStart,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                if (note.isEmpty()) {
                                    Text(
                                        text = "Write here!",
                                        style = TextStyle(
                                            fontSize = 13.sp,
                                            fontFamily = fontFamily,
                                            fontWeight = FontWeight(300),
                                            color = Color(0xFF16423C),
                                        )
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )
//
//                    Spacer(Modifier.size(15.dp))
//
//                    // Tombol "Simpan"
//                    Box(
//                        modifier = Modifier
//                            .clickable { /* Handle click action */ }
//                            .background(color = TextDark, shape = RoundedCornerShape(16.dp))
//                            .clip(RoundedCornerShape(16.dp))
//                            .padding(horizontal = 20.dp, vertical = 8.dp)
//                            .align(Alignment.End) // posisikan tombol ke kanan
//                    ) {
//                        Text(
//                            "Simpan",
//                            color = TextLight,
//                            fontFamily = fontFamily,
//                            fontSize = 10.sp
//                        )
//                    }
                }
            }
        }

    }
}

@Composable
fun CardNoteActivity(activity :Activity) {
    var isOpened by remember { mutableStateOf(false) }
    var noteText by remember { mutableStateOf(activity.note) }
    Card(
        colors = CardDefaults.cardColors(BackGround2),
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp), // Bentuk sudut card
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        // Bagian header card
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(TextMedium, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)) // Sesuaikan bentuk sudut
                .padding(16.dp, 3.dp)
                .height(27.dp)
        ) {
            val (judulText, editIcon) = createRefs()
            Text(
                text = activity.name,
                fontSize = 12.sp,
                fontWeight = FontWeight(600),
                fontFamily = fontFamily,
                color = TextDark,
                modifier = Modifier
                    .constrainAs(judulText) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top, margin = 7.dp)
                        bottom.linkTo(parent.bottom, margin = 7.dp)
                    }
            )

            Icon(
                painter = painterResource(id = R.drawable.maximize),
                contentDescription = "edit",
                tint = TextDark,
                modifier = Modifier
                    .size(17.dp)
                    .constrainAs(editIcon) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .clickable{
                        isOpened = !isOpened
                    }
            )
        }

        Box(
            modifier = Modifier
                .background(TextLight)
                .fillMaxWidth()
                .height(if (!isOpened) 60.dp else 800.dp)
                .padding(11.dp)
        ) {
            if (!isOpened) {
                Box(
                    modifier = Modifier
                        .background(TextLight, shape = RoundedCornerShape(8.dp))
                        .border(1.dp, TextMedium, shape = RoundedCornerShape(8.dp))
                        .fillMaxSize()
                        .padding(6.dp)
                ) {
                    if (noteText.isEmpty()) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "Write a note",
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight(300),
                                    color = Color(0xFF16423C),
                                )
                            )
                        }
                    } else {
                        Text(
                            text = noteText,
                            style = TextStyle(
                                fontSize = 8.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight(300),
                                color = Color(0xFF16423C),
                            )
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(6.dp),
//                    verticalArrangement = Arrangement.SpaceBetween // memastikan elemen diatur secara vertikal
                ) {
                    // TextField
                    BasicTextField(
                        value = noteText,
                        onValueChange = { query ->
                            noteText = query

                        },
                        modifier = Modifier
                            .background(TextLight, shape = RoundedCornerShape(8.dp))
                            .border(1.dp, TextMedium, shape = RoundedCornerShape(8.dp))
                            .fillMaxWidth()
                            .weight(1f) // memastikan TextField mengambil ruang yang cukup
                            .padding(6.dp)
                            .verticalScroll(rememberScrollState()),
                        decorationBox = { innerTextField ->
                            Box(
                                contentAlignment = if(noteText.isEmpty()) Alignment.Center else Alignment.TopStart,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                if (noteText.isEmpty()) {
                                    Text(
                                        text = "Write here!",
                                        style = TextStyle(
                                            fontSize = 13.sp,
                                            fontFamily = fontFamily,
                                            fontWeight = FontWeight(300),
                                            color = Color(0xFF16423C),
                                        )
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )
//
//                    Spacer(Modifier.size(15.dp))
//
//                    // Tombol "Simpan"
//                    Box(
//                        modifier = Modifier
//                            .clickable { /* Handle click action */ }
//                            .background(color = TextDark, shape = RoundedCornerShape(16.dp))
//                            .clip(RoundedCornerShape(16.dp))
//                            .padding(horizontal = 20.dp, vertical = 8.dp)
//                            .align(Alignment.End) // posisikan tombol ke kanan
//                    ) {
//                        Text(
//                            "Simpan",
//                            color = TextLight,
//                            fontFamily = fontFamily,
//                            fontSize = 10.sp
//                        )
//                    }
                }
            }
        }

    }
}


//
//@Preview
//@Composable
//fun CardNotesPreview() {
//    CardNoteActivity(note = "Tes")
//}