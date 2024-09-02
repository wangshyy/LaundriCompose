package com.laundri.wsy.ui.main

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.laundri.wsy.R
import com.laundri.wsy.ui.MainViewModel
import com.laundri.wsy.ui.customerservice.CustomerServiceScreen
import com.laundri.wsy.ui.home.HomeScreen
import com.laundri.wsy.ui.mine.MineScreen
import com.laundri.wsy.ui.order.OrderScreen
import com.laundri.wsy.ui.theme.ColorBottomBavItemSelected
import com.laundri.wsy.ui.theme.ColorBottomBavItemUnselected
import com.laundri.wsy.ui.theme.Primary

/**
 *  author : wsy
 *  date   : 2024/6/5
 *  desc   :
 */
private const val TAG = "MainScreen"
private lateinit var mainViewModel: MainViewModel

@Composable
fun MainScreen(
    bottomNavController: NavHostController,
    screenNavController: NavHostController,
    viewModel: MainViewModel
) {
    mainViewModel = viewModel
    val pages = listOf(
        Page(
            title = stringResource(id = R.string.home),
            route = "home",
            contentView = { HomeScreen(screenNavController) }),
        Page(title = stringResource(id = R.string.order),
            route = "order",
            contentView = { OrderScreen() }),
        Page(
            title = stringResource(id = R.string.customerService),
            route = "customer",
            contentView = { CustomerServiceScreen() }
        ),
        Page(title = stringResource(id = R.string.mine),
            route = "mine",
            contentView = { MineScreen() }
        )
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
                    startDestination = "home",
                ) {
                    pages.forEachIndexed { index, page ->
                        composable(page.route) {
                            page.contentView()
                        }
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        colors = TopAppBarColors(Color.White, Primary, Primary, Primary, Primary),
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = mainViewModel.title.value,
                color = Color.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
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
            "home"
        ),
        BottomNavItem(
            stringResource(id = R.string.order),
            painterResource(R.drawable.icon_order),
            "order"
        ),
        BottomNavItem(
            stringResource(id = R.string.customerService),
            painterResource(R.drawable.icon_customer_service),
            "customer"
        ),
        BottomNavItem(
            stringResource(id = R.string.mine),
            painterResource(R.drawable.icon_mine),
            "mine"
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
                    // 重置侧滑计数
                    mainViewModel.userSideSwipeCount = 0

                    if (selectedItem == index) return@BottomNavigationItem
                    selectedItem = index
                    mainViewModel.title.value = items[index].label
                    navController.navigate(bottomNavItem.route) {
                        // 清空回退栈
                        navController.popBackStack()
                    }
                }
            )
        }
    }
}

data class Page(
    val title: String,
    val route: String,
    val contentView: @Composable () -> Unit
)

data class BottomNavItem(
    val label: String,
    val icon: Painter,
    val route: String
)