package com.wsy.laundricompose.ui.component

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.wsy.laundricompose.ui.theme.Primary

/**
 * 状态栏组件
 *
 * @param color 状态栏颜色
 * @param useDarkIcons 是否使用黑色图标
 */
@Composable
fun SystemBars(color: Color = Primary, useDarkIcons: Boolean = false) {
    val systemUiController = rememberSystemUiController()
//        val useDarkIcons = !isSystemInDarkTheme()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = color,
            darkIcons = useDarkIcons,
            isNavigationBarContrastEnforced = false
        )
    }
}