package com.wsy.laundricompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.wsy.laundricompose.ui.home.smartbooking.SmartBookingScreen
import com.wsy.laundricompose.ui.main.MainScreen
import com.wsy.laundricompose.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val bottomNavController = rememberAnimatedNavController()
            val screenNavController = rememberAnimatedNavController()
            LaundriComposeTheme {
                // A surface container using the 'background' color from the theme
                TransparentSystemBars()
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimatedNavHost(
                        navController = screenNavController,
                        startDestination = "main"
                    ) {
                        composable(route = "main",
                            enterTransition = {
                                fadeIn(initialAlpha = 1f)
                            },
                            exitTransition = {
                                fadeOut(targetAlpha = 1f)
                            },
                            popEnterTransition = {
                                fadeIn(initialAlpha = 1f)
                            },
                            popExitTransition = {
                                fadeOut(targetAlpha = 1f)
                            }) {
                            MainScreen(bottomNavController, screenNavController)
                        }
                        composable("smartBooking",
                            enterTransition = {
                                fadeIn(initialAlpha = 1f)
                            },
                            exitTransition = {
                                slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(
                                    animationSpec = tween(700)
                                )
                            },
                            popEnterTransition = {
                                fadeIn(initialAlpha = 1f)
                            },
                            popExitTransition = {
                                slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut(
                                    animationSpec = tween(700)
                                )
                            }
                        ) {
                            SmartBookingScreen()
                        }
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
}

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