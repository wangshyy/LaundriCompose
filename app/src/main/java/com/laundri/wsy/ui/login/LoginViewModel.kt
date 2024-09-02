package com.laundri.wsy.ui.login

import androidx.lifecycle.ViewModel
import com.laundri.wsy.http.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *  author : wsy
 *  date   : 2024/8/30
 *  desc   :
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {

}