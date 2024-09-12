package com.laundri.wsy.ui.login

import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.laundri.wsy.R
import com.laundri.wsy.ext.logE
import com.laundri.wsy.ui.MainViewModel
import com.laundri.wsy.ui.theme.ColorTextDefault
import com.laundri.wsy.ui.theme.ColorTextHint
import com.laundri.wsy.ui.theme.Primary
import com.laundri.wsy.wechat.WechatHelper

/**
 *  author : wsy
 *  date   : 2024/6/28
 *  desc   : 登陆页面
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LoginScreen(
    mainViewModel: MainViewModel
//    screenNavController: NavHostController,
) {
    val mockCode = "061NLZ1008vsNS1Y3R200dz1Ux2NLZ1B"

    val loginViewModel: LoginViewModel = hiltViewModel()
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    mainViewModel.wechatCode.observe(LocalLifecycleOwner.current) {
        it.logE("LoginScreen")
        loginViewModel.authorizedLogin(authType = "wechat", code = it, clientId = "32423432")
    }

    return Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .border(
                1.dp,
                Color.White,
                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var isCheck by remember {
            mutableStateOf(false)
        }
        var openDialog by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .height(80.dp)
        )
        Image(
            modifier = Modifier
                .height(120.dp)
                .width(120.dp),
            painter = rememberAsyncImagePainter(
                model = "http://laundri.oss-cn-hangzhou.aliyuncs.com/resuource/customerService.gif",
                imageLoader = imageLoader
            ), contentDescription = "mascot"
        )
        Box(
            modifier = Modifier
                .height(20.dp)
        )
        Text(
            text = "欢迎使用兰德力",
            fontWeight = FontWeight(500),
            fontSize = 20.sp,
            color = ColorTextDefault
        )
        Box(
            modifier = Modifier
                .height(40.dp)
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(46.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            shape = CircleShape,
            onClick = {
                if (!isCheck) {
                    openDialog = true
                    return@Button
                }
                getAuthCode()
            }) {
            Text(
                text = "登陆",
                fontSize = 16.sp,
                color = Color.White
            )
        }
        Box(
            modifier = Modifier
                .height(20.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = if (!isCheck) {
                    painterResource(id = R.drawable.icon_unselect)
                } else {
                    painterResource(id = R.drawable.icon_select)
                }, contentDescription = null,
                Modifier
                    .width(14.dp)
                    .height(14.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }) {
                        isCheck = !isCheck
                    }
            )
            Box(
                modifier = Modifier
                    .width(6.dp)
            )
            Text(
                text = "我已阅读并同意《兰德力自助洗服务协议》",
                fontSize = 12.sp,
                color = ColorTextHint
            )
        }
        AnimatedVisibility(
            visible = openDialog
        ) {
            Dialog(
                onDismissRequest = {},
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)

                ) {
                    Column(
                        modifier = Modifier
                            .animateEnterExit(
                                enter = slideInVertically(
                                    initialOffsetY = { it },
                                    animationSpec = tween(300)
                                ),
                                exit = slideOutVertically(
                                    targetOffsetY = { it },
                                    animationSpec = tween(300)
                                )
                            )
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                            .background(Color.White)
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "服务协议及隐私保护",
                            fontSize = 16.sp,
                            color = ColorTextDefault,
                            fontWeight = FontWeight(600)
                        )
                        Box(modifier = Modifier.height(20.dp))
                        Text(
                            text = "为了保障您的合法权益，请您阅读并同意以下协议\n《兰德力自助洗服务协议》",
                            fontSize = 12.sp,
                            color = ColorTextDefault
                        )
                        Box(modifier = Modifier.height(20.dp))
                        Row {
                            Button(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(46.dp),
                                border = BorderStroke(1.dp, Primary),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                shape = CircleShape,
                                elevation = null,
                                onClick = {
                                    openDialog = false
                                }) {
                                Text(
                                    text = "不同意",
                                    fontSize = 16.sp,
                                    color = Primary,
                                )
                            }
                            Box(modifier = Modifier.width(10.dp))
                            Button(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(46.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                                shape = CircleShape,
                                elevation = null,
                                onClick = {
                                    openDialog = false
                                    isCheck = true


//                                    getAuthCode()
                                    // 模拟获取 code
                                    mainViewModel.updateWechatCode(mockCode)
                                }) {
                                Text(
                                    text = "同意",
                                    fontSize = 16.sp,
                                    color = Color.White,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * 获取微信授权码
 *
 */
private fun getAuthCode() {
    // 获取微信登陆 code
    // 在 MainActivity 接收 code
    WechatHelper.instance.getAuthCode()
}