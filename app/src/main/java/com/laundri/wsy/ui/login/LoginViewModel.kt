package com.laundri.wsy.ui.login

import com.laundri.wsy.bean.request.AuthorizedLoginRequest
import com.laundri.wsy.ext.logE
import com.laundri.wsy.http.base.BaseViewModel
import com.laundri.wsy.http.base.checkResult
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
) : BaseViewModel() {
    fun authorizedLogin(authType: String, code: String, clientId: String) {
        launch {
            val authorizedLoginRequest =
                AuthorizedLoginRequest(authType = authType, code = code, clientId = clientId)
            repository.authorizedLogin(authorizedLoginRequest).checkResult({
                "success: ${it.toString()}".logE(TAG)
            }, {
                "error: ${it.toString()}".logE(TAG)
            })
        }
    }
}