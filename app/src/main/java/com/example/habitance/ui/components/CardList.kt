import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.example.habitance.R
import com.example.habitance.navbar.Screen
import com.example.habitance.ui.screens.add_activity.Activity
import com.example.habitance.ui.screens.add_activity.CategoryActivity
import com.example.habitance.ui.screens.detailactivity.showDate
import com.example.habitance.ui.theme.BackGround
import com.example.habitance.ui.theme.BackGround2
import com.example.habitance.ui.theme.TextDark
import com.example.habitance.ui.theme.TextMedium
import com.example.habitance.ui.theme.fontFamily
import com.google.firebase.Timestamp
import java.util.concurrent.TimeUnit

@Composable
fun CardList(
    navController: NavController,
    activity: Activity,
    onNavigateToDetail: () -> Unit
) {
    val now = Timestamp.now().toDate()
    val createdDate = activity.start.toDate()
    val diffInMillis = now.time - createdDate.time
    val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis).toInt()
    val progress = try {
        activity.progress[diffInDays.toString()]!!
    } catch (_: Exception) {
        Log.d("CardList", "Error getting progress:")
        0
    }

    Card(
        colors = CardDefaults.cardColors(BackGround2),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onNavigateToDetail()
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .background(TextMedium)
            .padding(16.dp, 3.dp)
        ) {
            val (judulText, targetText, targetText2, editIcon) = createRefs()
            Text(
                text = activity.name,
                fontSize = 13.sp,
                fontWeight = FontWeight(500),
                fontFamily = fontFamily,
                color = TextDark,
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
                color = TextDark,
                modifier = Modifier
                    .constrainAs(targetText){
                        end.linkTo(targetText2.end)
                        bottom.linkTo(targetText2.top)
                        top.linkTo(parent.top)
                    }
            )
            Text(
                text = "${progress}/${activity.target}",
                fontSize = 15.sp,
                fontFamily = fontFamily,
                color = TextDark,
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
        ) {
            val (targetIcon, targetText, startIcon, startText, endIcon, endText, fireIcon, noteButton, notePreview) = createRefs()
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
                text = "${activity.target} ${activity.unit}/${activity.periode}",
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
                text = activity.start.showDate(),
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
                text = activity.end.showDate(),
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
            Box(
                modifier = Modifier
                    .constrainAs(fireIcon){
                        end.linkTo(parent.end, margin = 10.dp)
                        top.linkTo(targetIcon.top)
                    }
                    .clipToBounds(),
                contentAlignment = Alignment.BottomCenter
            ) {
                val target = activity.target
                val size = 30.dp
                val targetPercentage = target.toFloat() / progress.toFloat()
                val fireHeight = (if(targetPercentage >= 1f || activity.category == CategoryActivity.Buruk) targetPercentage else 1f)

                val containerOffset = if(targetPercentage != Float.NEGATIVE_INFINITY)
                    size - size / fireHeight
                else 0.dp

                val imageOffset = if(targetPercentage != Float.NEGATIVE_INFINITY)
                    -size + size / fireHeight
                else 0.dp
                val infiniteTransition = rememberInfiniteTransition(label = activity.id)
                val rotationZ by infiniteTransition.animateFloat(
                    initialValue = -15f,
                    targetValue = 15f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = 300, // Durasi animasi (1 detik)
                            easing = LinearEasing // Easing linear
                        ),
                        repeatMode = RepeatMode.Reverse // Kembali ke nilai awal setelah selesai
                    ),
                    label = ""
                )
                Box(
                    modifier = Modifier
                        .graphicsLayer(
                            rotationZ = if(targetPercentage <= 1f && targetPercentage > 0f) rotationZ else 0f
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.fire_off),
                        contentDescription = "fire off",
                        modifier = Modifier
                            .size(size)
                    )
                    Box(
                        modifier = Modifier
                            .size(size)
                            .offset(y = containerOffset)
                            .clipToBounds()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.fire_on),
                            contentDescription = "fire on",
                            modifier = Modifier
                                .size(size)
                                .offset(y = imageOffset) // Geser gambar ke atas sebanyak setengah tinggi
                        )
                    }
                }
            }
            Button(
                onClick = { navController.navigate("addnote/${activity.id}/${activity.name}") },
                modifier = Modifier
                    .size(35.dp) // Ukuran lingkaran
                    .clip(CircleShape) // Membuat bentuk lingkaran
                    .constrainAs(noteButton) {
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

//@Preview
//@Composable
//fun CardListPreview() {
//    CardList(Activity(), {})
//}


