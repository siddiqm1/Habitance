package com.example.habitance.ui.components


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.habitance.ui.screens.note.Note


@Composable
fun CardNoteActivity(note: Note, activityName: String) {
    val formattedDate = note.timestamp.toDate().formatToString()

    Card(
        colors = CardDefaults.cardColors(Color(0xFFF0F0F0)),
        modifier = Modifier
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        ConstraintLayout(modifier = Modifier.padding(16.dp)) {
            val (activityTitle, contentText, dateText) = createRefs()

            Text(
                text = activityName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(activityTitle) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
            )

            Text(
                text = note.content,
                fontSize = 14.sp,
                modifier = Modifier.constrainAs(contentText) {
                    start.linkTo(parent.start)
                    top.linkTo(activityTitle.bottom, margin = 8.dp)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = formattedDate,
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.constrainAs(dateText) {
                    start.linkTo(parent.start)
                    top.linkTo(contentText.bottom, margin = 8.dp)
                }
            )
        }
    }
}

// Fungsi untuk format tanggal
fun java.util.Date.formatToString(): String {
    val sdf = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
    return sdf.format(this)
}
