package com.wsy.laundri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wsy.laundri.ui.theme.*

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            LaundriComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = { BottomNavigationBar(navController) },
                        content = { paddingValues ->
                            Column(modifier = Modifier.fillMaxSize()) {
                                NavHost(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(paddingValues),
                                    navController = navController,
                                    startDestination = "page1",
                                ) {
                                    composable("page1") {
                                        TopAppBar(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            title = {
                                                Text(
                                                    modifier = Modifier
                                                        .fillMaxWidth(),
                                                    text = "首页",
                                                    color = Color.White,
                                                    textAlign = TextAlign.Center
                                                )
                                            },
                                            backgroundColor = Primary,
                                        )
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize(), contentAlignment = Alignment.Center
                                        ) {
                                            Text(text = "page 1")
                                        }
                                    }
                                    composable("page2") {
                                        Text(text = "page 2")
                                    }
                                    composable("page3") {
                                        Text(text = "page 3")
                                    }
                                    composable("page4") {
                                        Text(text = "page 4")
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    var selectedItem by remember {
        mutableStateOf(0)
    }

    val items = listOf(
        BottomNavItem(
            stringResource(id = R.string.home),
            painterResource(R.drawable.icon_home_unselected),
            "page1"
        ),
        BottomNavItem(
            stringResource(id = R.string.order),
            painterResource(R.drawable.icon_order_unselected),
            "page2"
        ),
        BottomNavItem(
            stringResource(id = R.string.customerService),
            painterResource(R.drawable.icon_customer_service_unselected),
            "page3"
        ),
        BottomNavItem(
            stringResource(id = R.string.mine),
            painterResource(R.drawable.icon_mine_unselected),
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
                    selectedItem = index
                    navController.navigate(bottomNavItem.route)
                }
            )

        }
    }
}

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