package com.wsy.laundricompose.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *  author : wsy
 *  date   : 2024/2/23
 *  desc   :
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val title = mutableStateOf("首页")
}