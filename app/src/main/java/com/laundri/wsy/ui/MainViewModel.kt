package com.laundri.wsy.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laundri.wsy.http.repository.LoginRepository
import com.laundri.wsy.http.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 *  author : wsy
 *  date   : 2024/2/23
 *  desc   :
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _wechatCode = MutableLiveData<String>()
    val wechatCode: LiveData<String>
        get() = _wechatCode

    // 用户侧滑次数
    var userSideSwipeCount = 0

    val title = mutableStateOf("首页")

    fun updateWechatCode(code: String) {
        _wechatCode.value = code
    }
}