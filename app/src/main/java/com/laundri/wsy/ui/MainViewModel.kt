package com.laundri.wsy.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.laundri.wsy.http.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *  author : wsy
 *  date   : 2024/2/23
 *  desc   :
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    // 用户侧滑次数
    var userSideSwipeCount = 0

    val title = mutableStateOf("首页")
}