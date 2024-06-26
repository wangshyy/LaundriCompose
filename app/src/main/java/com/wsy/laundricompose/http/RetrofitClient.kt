package com.wsy.laundricompose.http

import com.wsy.laundricompose.BuildConfig
import com.wsy.laundricompose.http.base.BaseRetrofitClient
import okhttp3.OkHttpClient
import javax.inject.Inject

/**
 *  author : wsy
 *  date   : 2024/6/26
 *  desc   :
 */
class RetrofitClient @Inject constructor(): BaseRetrofitClient() {
    companion object {
        // 请求地址
        const val BASE_URL = BuildConfig.BASE_URL
    }

    val service by lazy { getService(ApiService::class.java, BASE_URL) }
    override fun handleBuilder(builder: OkHttpClient.Builder) {

    }
}