package com.laundri.wsy.http.repository

import com.laundri.wsy.bean.request.AuthorizedLoginRequest
import com.laundri.wsy.bean.response.AuthorizedLoginResponse
import com.laundri.wsy.http.ApiService
import com.laundri.wsy.http.base.BaseRepository
import com.laundri.wsy.http.base.Result
import javax.inject.Inject

/**
 *  author : wsy
 *  date   : 2024/6/27
 *  desc   :
 */

class LoginRepository @Inject constructor(private val service: ApiService) : BaseRepository() {
    suspend fun authorizedLogin(requestParams: AuthorizedLoginRequest): Result<AuthorizedLoginResponse> {
        return safeApiCall(
            call = {
                executeResponse(
                    service.authorizedLogin(
                        requestParams.agent,
                        requestParams.authType,
                        requestParams.clientId,
                        requestParams.code,
                        requestParams.mer
                    )
                )
            },
            errorMessage = "登录失败"
        )
    }
}