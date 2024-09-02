package com.laundri.wsy.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * 状态栏组件
 *
 * @param color 状态栏颜色
 * @param useDarkIcons 是否使用黑色图标
 */
@Composable
fun SystemBars(color: Color = Color.Transparent, useDarkIcons: Boolean = false) {
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