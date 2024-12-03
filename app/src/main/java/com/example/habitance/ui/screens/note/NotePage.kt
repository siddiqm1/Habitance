package com.example.habitance.ui.screens.note


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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import com.example.habitance.R
import com.example.habitance.navbar.Screen
import com.example.habitance.ui.theme.BackGround
import com.example.habitance.ui.theme.BackGround2
import com.example.habitance.ui.theme.Border
import com.example.habitance.ui.theme.fontFamily

@Composable
fun NotePage(navController: NavHostController) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier
        .fillMaxSize()
        .background(BackGround)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = { navController.navigate(Screen.HomeScreen.route)},
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "back button",
                    modifier = Modifier.size(25.dp)
                )
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .offset(x = (-30).dp),
                contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier.size(80.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)) {

            Column(modifier = Modifier
                .width(378.dp)
                .height(720.dp)
                .background(BackGround2)
                .padding(vertical = 24.dp, horizontal = 24.dp)) {

                Text(
                    text = "NOTES",
                    fontSize = 26.sp,
                    fontFamily = fontFamily,
                    color = Border,
                    fontWeight = FontWeight(600)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Card(modifier = Modifier
                    .wrapContentSize(Alignment.Center)
                    .border(width = 2.dp, color = Border, shape = RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(5.dp)) {

                    Column(modifier = Modifier
                        .fillMaxWidth()) {

                        Column(modifier = Modifier
                            .background(Border)
                            .fillMaxWidth()
                            .padding(16.dp)){
                            Text(
                                text = "Seseorang's Notes",
                                fontSize = 18.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight(500)
                            )
                        }

                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .background(BackGround)
                            .padding(16.dp)
                            .verticalScroll(scrollState)){

                            Text(
                                text = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Quo voluptates reprehenderit, commodi sit consectetur nulla dolores accusantium delectus officia consequatur nesciunt voluptas, voluptatum ipsam exercitationem tempora voluptatem eius amet provident!\n" +
                                        "Saepe fugit ab facilis eligendi. Blanditiis nam, quasi consectetur repudiandae dignissimos dolores pariatur consequuntur dolorum quo rerum deleniti minus repellat maiores a fugiat iure cupiditate amet! Quos sed hic libero!\n" +
                                        "Soluta eveniet laborum perspiciatis hic illo ratione neque, incidunt explicabo necessitatibus veritatis repellendus! Distinctio sapiente repellendus voluptatem inventore, saepe, a consequatur atque molestias laudantium, perspiciatis quae nemo eligendi quidem porro.\n" +
                                        "Soluta eveniet laborum perspiciatis hic illo ratione neque, incidunt explicabo necessitatibus veritatis repellendus! Distinctio sapiente repellendus voluptatem inventore, saepe, a consequatur atque molestias laudantium, perspiciatis quae nemo eligendi quidem porro.\n" +
                                        "Soluta eveniet laborum perspiciatis hic illo ratione neque, incidunt explicabo necessitatibus veritatis repellendus! Distinctio sapiente repellendus voluptatem inventore, saepe, a consequatur atque molestias laudantium, perspiciatis quae nemo eligendi quidem porro.\n" +
                                        "Soluta eveniet laborum perspiciatis hic illo ratione neque, incidunt explicabo necessitatibus veritatis repellendus! Distinctio sapiente repellendus voluptatem inventore, saepe, a consequatur atque molestias laudantium, perspiciatis quae nemo eligendi quidem porro.\n" +
                                        "Soluta eveniet laborum perspiciatis hic illo ratione neque, incidunt explicabo necessitatibus veritatis repellendus! Distinctio sapiente repellendus voluptatem inventore, saepe, a consequatur atque molestias laudantium, perspiciatis quae nemo eligendi quidem porro.\n" +
                                        "Deleniti, dicta. Neque, cumque. Tenetur possimus dolore excepturi! Molestias totam, minima quidem natus optio ipsum debitis itaque ullam consectetur perferendis! Fugit aspernatur laboriosam culpa at ut quibusdam ratione impedit quisquam?",
                                fontSize = 12.sp,
                                fontFamily = fontFamily,
                                lineHeight = 16.sp)
                        }

                    }

                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotePagePreview() {
    NotePage(navController = rememberNavController())
}


