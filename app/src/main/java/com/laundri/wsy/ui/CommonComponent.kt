package com.laundri.wsy.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.laundri.wsy.ui.theme.Primary

/**
 *  author : wsy
 *  date   : 2024/2/27
 *  desc   :
 */

/**
 * 页面顶层布局
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(
    title: String,
    bottomBar: (@Composable () -> Unit)? = null,
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(title) },
        bottomBar = bottomBar ?: {},
        content = { paddingValues ->
            content(paddingValues)
        }
    )
}

@Composable
private fun TopBar(title: String) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = title,
                color = Color.White,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        },
        backgroundColor = Primary,
    )
}