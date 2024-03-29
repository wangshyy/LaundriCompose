package com.wsy.laundricompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.wsy.laundricompose.R
import com.wsy.laundricompose.ui.customerservice.CustomerServiceScreen
import com.wsy.laundricompose.ui.home.HomeScreen
import com.wsy.laundricompose.ui.home.smartbooking.SmartBookingScreen
import com.wsy.laundricompose.ui.mine.MineScreen
import com.wsy.laundricompose.ui.order.OrderScreen
import com.wsy.laundricompose.ui.theme.*

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val bottomNavController = rememberNavController()
            val screenNavController = rememberNavController()
            LaundriComposeTheme {
                // A surface container using the 'background' color from the theme
                TransparentSystemBars()
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = screenNavController, startDestination = "Main") {
                        composable("Main") { MainScreen(bottomNavController, screenNavController) }
                        composable("SmartBooking") { SmartBookingScreen() }
                    }
                }
            }
        }
    }

    @Composable
    fun TransparentSystemBars() {
        val systemUiController = rememberSystemUiController()
//        val useDarkIcons = !isSystemInDarkTheme()
        val useDarkIcons = false
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Primary,
                darkIcons = useDarkIcons,
                isNavigationBarContrastEnforced = false
            )
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun MainScreen(
        bottomNavController: NavHostController,
        screenNavController: NavHostController
    ) {
        val pages = listOf(
            Page(
                title = stringResource(id = R.string.home),
                contentView = { HomeScreen(screenNavController) }),
            Page(title = stringResource(id = R.string.order), contentView = { OrderScreen() }),
            Page(
                title = stringResource(id = R.string.customerService),
                contentView = { CustomerServiceScreen() }
            ),
            Page(title = stringResource(id = R.string.mine), contentView = { MineScreen() })
        )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar() },
            bottomBar = { BottomNavigationBar(bottomNavController) },
            content = { paddingValues ->
                Column(modifier = Modifier.fillMaxSize()) {
                    NavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        navController = bottomNavController,
                        startDestination = "page1",
                    ) {
                        pages.forEachIndexed { index, page ->
                            composable("page${index + 1}") {
                                page.contentView()
                            }
                        }
                    }
                }
            }
        )
    }

    @Composable
    private fun TopBar() {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            title = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = viewModel.title.value,
                    color = Color.White,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            },
            backgroundColor = Primary,
        )
    }

    @Composable
    private fun BottomNavigationBar(navController: NavHostController) {
        var selectedItem by remember {
            mutableStateOf(0)
        }
        val items = listOf(
            BottomNavItem(
                stringResource(id = R.string.home),
                painterResource(R.drawable.icon_home),
                "page1"
            ),
            BottomNavItem(
                stringResource(id = R.string.order),
                painterResource(R.drawable.icon_order),
                "page2"
            ),
            BottomNavItem(
                stringResource(id = R.string.customerService),
                painterResource(R.drawable.icon_customer_service),
                "page3"
            ),
            BottomNavItem(
                stringResource(id = R.string.mine),
                painterResource(R.drawable.icon_mine),
                "page4"
            )
        )

        BottomNavigation(
            backgroundColor = Color.White,
        ) {
            items.forEachIndexed { index, bottomNavItem ->
                BottomNavigationItem(
                    selected = selectedItem == index,
                    unselectedContentColor = ColorBottomBavItemUnselected,
                    selectedContentColor = ColorBottomBavItemSelected,
                    label = {
                        Text(
                            text = bottomNavItem.label,
                            fontSize = if (selectedItem == index) 12.sp else 10.sp,
                            color = if (selectedItem == index) ColorBottomBavItemSelected else ColorBottomBavItemUnselected
                        )
                    },
                    icon = {
                        Icon(bottomNavItem.icon, contentDescription = bottomNavItem.label)
                    },
                    onClick = {
                        if (selectedItem == index) return@BottomNavigationItem
                        selectedItem = index
                        viewModel.title.value = items[index].label
                        navController.navigate(bottomNavItem.route)
                    }
                )
            }
        }
    }
}


data class Page(
    val title: String,
    val contentView: @Composable () -> Unit
)

data class BottomNavItem(
    val label: String,
    val icon: Painter,
    val route: String
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LaundriComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
        }
    }
}