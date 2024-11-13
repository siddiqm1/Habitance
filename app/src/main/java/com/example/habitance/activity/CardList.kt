package com.example.habitance.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habitance.R
import com.example.habitance.ui.theme.BackGround
import com.example.habitance.ui.theme.Border
import com.example.habitance.ui.theme.NewGreen

@Composable
fun CardView() {
    var text by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
            .border(width = 2.dp, color = Border, shape = RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Column(
                modifier = Modifier
                    .background(Border)
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Seseorang's Notes",
                        fontSize = 18.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(500)
                    )

                    Column(verticalArrangement = Arrangement.Top) {
                        Text(text = "Target")
                        Text(text = "35/30")
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackGround)
                    .padding(16.dp)
            ) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.target),
                        contentDescription = "target",
                        modifier = Modifier.size(25.dp)
                    )

                    Text(
                        text = "30 Halaman / Day",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 8.dp),
                        fontFamily = fontFamily,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp)
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.flag),
                            contentDescription = "target mulai",
                            modifier = Modifier.size(15.dp)
                        )

                        Text("03/01/2024")
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.flag2),
                            contentDescription = "target mulai",
                            modifier = Modifier.size(15.dp)
                        )

                        Text("03/01/2024")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

                // Row with Button on the left and TextField on the right
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier.size(45.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape),
                            colors = ButtonDefaults.buttonColors(containerColor = NewGreen)
                        ) {}

                        Image(
                            painter = painterResource(R.drawable.add_paper),
                            contentDescription = "button",
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        placeholder = { Text("Enter notes") }
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun CardViewPreview() {
    CardView()
}