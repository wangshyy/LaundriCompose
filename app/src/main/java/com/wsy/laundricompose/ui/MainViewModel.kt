package com.wsy.laundricompose.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/**
 *  author : wsy
 *  date   : 2024/2/23
 *  desc   :
 */
class MainViewModel: ViewModel() {
    val title = mutableStateOf("首页")
}