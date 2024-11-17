import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.habitance.R
import com.example.habitance.navbar.BottomBarScreen
import com.example.habitance.navbar.BottomNavGraph
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NavBarWithPager() {
    val navController = rememberNavController()
    val pagerState = rememberPagerState(initialPage = 0) // Mulai dari halaman pertama
    val coroutineScope = rememberCoroutineScope()

    // Daftar halaman
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Activity,
        BottomBarScreen.Register,
        BottomBarScreen.Note
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomBar(navController = navController, screens = screens, pagerState = pagerState)
        },
        containerColor = Color.White
    ) { paddingValues ->
        HorizontalPager(
            count = screens.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { page ->
            // Konten di dalam halaman
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                BottomNavGraph(navController = navController)
            }
        }
    }
}

@Composable
@OptIn(ExperimentalPagerApi::class)
fun BottomBar(
    navController: NavHostController,
    screens: List<BottomBarScreen>,
    pagerState: PagerState
) {
    val coroutineScope = rememberCoroutineScope()
    val currentPage = pagerState.currentPage

    Row(
        modifier = Modifier
            .fillMaxWidth()

            .background(colorResource(id = R.color.navbar))
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEachIndexed { index, screen ->
            val selected = currentPage == index

            val background =
                if (selected) colorResource(id = R.color.navbar_icon_background) // Warna merah
                else Color.Transparent

            val contentColor =
                if (selected) Color.White else Color.Black

            Box(
                modifier = Modifier
                    .height(40.dp)
                    .clip(CircleShape)
                    .background(background)
                    .clickable(onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(index) // Pindah ke halaman sesuai navbar
                        }
                    })
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = if (selected) screen.icon_focused else screen.icon),
                        contentDescription = "icon",
                        tint = contentColor
                    )
                    if (selected) {
                        Text(
                            text = screen.title,
                            color = contentColor
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavBarWithPagerPreview() {
    NavBarWithPager()
}
