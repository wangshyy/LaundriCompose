package com.laundri.wsy.ui.login

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.laundri.wsy.R
import com.laundri.wsy.ui.theme.ColorTextDefault
import com.laundri.wsy.ui.theme.ColorTextHint
import com.laundri.wsy.ui.theme.Primary

/**
 *  author : wsy
 *  date   : 2024/6/28
 *  desc   : 登陆页面
 */

@Composable
fun LoginScreen(
    screenNavController: NavHostController,
) {
    val viewModel: LoginViewModel = hiltViewModel()
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    return Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
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
            colors = ButtonDefaults.buttonColors(backgroundColor = Primary),
            shape = CircleShape,
            onClick = {
                login()
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
            var isCheck by remember {
                mutableStateOf(false)
            }
            Image(
                painter = if (isCheck) {
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
    }
}

fun login() {

}