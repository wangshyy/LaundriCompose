package com.laundri.wsy.ui

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.laundri.wsy.BuildConfig
import com.laundri.wsy.ext.toast
import com.laundri.wsy.ui.component.SystemBars
import com.laundri.wsy.ui.home.smartbooking.SmartBookingScreen
import com.laundri.wsy.ui.login.LoginScreen
import com.laundri.wsy.ui.main.MainScreen
import com.laundri.wsy.ui.theme.*
import com.laundri.wsy.wechat.WechatHelper
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity(), IWXAPIEventHandler {

    private val viewModel: MainViewModel by viewModels()
    private var receiver: BroadcastReceiver? = null

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initWeChat()

        // 侧滑回调
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (viewModel.userSideSwipeCount >= 1) {
                    finish()
                    return
                }
                viewModel.userSideSwipeCount++
                toast(this@MainActivity, "再滑动一次退出程序")
            }
        })

//        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val bottomNavController = rememberAnimatedNavController()
            val screenNavController = rememberAnimatedNavController()
            LaundriComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimatedNavHost(
                        navController = screenNavController,
                        startDestination = if (intent.getBooleanExtra(
                                "isLogin",
                                false
                            )
                        ) "main" else "login"
                    ) {
                        // 登录页
                        composable(route = "login",
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
                            SystemBars(color = Color.White, useDarkIcons = true)
                            LoginScreen(screenNavController)
                        }
                        // 主页
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
                            }
                        ) {
                            SystemBars(color = Color.White, useDarkIcons = true)
                            MainScreen(bottomNavController, screenNavController, viewModel)
                        }
                        // 智能预定页
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

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    private fun initWeChat() {
        WechatHelper.instance.init(applicationContext)
        //建议动态监听微信启动广播进行注册到微信
        receiver = object : BroadcastReceiver() {
                override fun onReceive(p0: Context?, p1: Intent?) {
                    WechatHelper.instance.api.registerApp(BuildConfig.APPLICATION_ID)
                }
            }
        registerReceiver(receiver,
            IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP)
        )
        WechatHelper.instance.api.handleIntent(intent, this)
    }


    override fun onReq(p0: BaseReq?) {

    }

    override fun onResp(baseResp: BaseResp?) {
        when (baseResp?.errCode) {
            BaseResp.ErrCode.ERR_OK -> {
                val code = (baseResp as SendAuth.Resp).code
                toast(this, code)
            }
            // 用户拒绝授权
            BaseResp.ErrCode.ERR_AUTH_DENIED -> {
                toast(this, "用户拒绝授权")
            }
            // 用户取消
            BaseResp.ErrCode.ERR_USER_CANCEL -> {
                toast(this, "用户取消")
            }

            else -> {

            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
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