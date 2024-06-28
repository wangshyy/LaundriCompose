package com.wsy.laundricompose.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.wsy.laundricompose.R

/**
 *  author : wsy
 *  date   : 2024/6/28
 *  desc   :
 */

@Composable
fun LoginScreen(screenNavController: NavHostController) {
    return Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Image(painter = painterResource(R.drawable.icon_mascot), contentDescription = "mascot")
    }
}