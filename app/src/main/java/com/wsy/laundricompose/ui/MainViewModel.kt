package com.wsy.laundricompose.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.wsy.laundricompose.http.repository.MainRepository
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
    val title = mutableStateOf("首页")
}