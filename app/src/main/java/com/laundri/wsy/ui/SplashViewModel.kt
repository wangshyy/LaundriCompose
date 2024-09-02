package com.laundri.wsy.ui

import androidx.lifecycle.ViewModel
import com.laundri.wsy.http.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *  author : wsy
 *  date   : 2024/8/29
 *  desc   :
 */

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {
    val isLogin = false
}