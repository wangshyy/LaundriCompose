package com.wsy.laundricompose.ui.customerservice

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 *  author : wsy
 *  date   : 2024/2/23
 *  desc   :
 */

@Composable
fun CustomerServiceScreen() {
    return Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "page 3")
    }
}