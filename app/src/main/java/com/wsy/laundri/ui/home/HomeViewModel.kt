package com.wsy.laundri.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/**
 *  author : wsy
 *  date   : 2024/2/23
 *  desc   :
 */
class HomeViewModel : ViewModel() {
    val count = mutableStateOf(0)
    fun countPlus() {
        count.value++
    }
}