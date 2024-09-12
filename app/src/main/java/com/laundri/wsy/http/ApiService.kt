package com.laundri.wsy.http

import com.laundri.wsy.bean.response.AuthorizedLoginResponse
import com.laundri.wsy.http.base.Response
import retrofit2.http.*

/**
 *  author : wsy
 *  date   : 2024/6/26
 *  desc   :
 */
interface ApiService {
    /**
     * 授权登录
     *
     */
    @POST("/v2/login/authorizedLogin")
    suspend fun authorizedLogin(
        @Query("agent") agent: String,
        @Query("authType") authType: String,
        @Query("clientId") clientId: String,
        @Query("code") code: String,
        @Query("mer") mer: String,
    ): Response<AuthorizedLoginResponse>
}