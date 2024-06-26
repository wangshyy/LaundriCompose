package com.wsy.laundricompose.http.base

import com.wsy.laundricompose.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *  author : wsy
 *  date   : 2024/6/26
 *  desc   : 用于封装 Retrofit 的基本配置和网络请求的设置
 */
abstract class BaseRetrofitClient {
    companion object {
        // 请求超时
        const val TIME_OUT: Long = 5
    }

    // OkHttpClient 实例
    private val okHttpClient: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()

            // 日志拦截器
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    // 在 DEBUG 模式下记录详细日志
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    // 在非 DEBUG 模式下只记录基本日志
                    HttpLoggingInterceptor.Level.BASIC
                }
            }
            builder
                .addInterceptor(loggingInterceptor)
                .callTimeout(TIME_OUT, TimeUnit.SECONDS)

            // 允许子类自定义 OkHttpClient 配置
            handleBuilder(builder)

            return builder.build()
        }

    /**
     * 子类必须实现的方法，用于处理 OkHttpClient.Builder 的配置
     *
     * @param builder OkHttpClient.Builder 实例，用于添加自定义配置
     */
    protected abstract fun handleBuilder(builder: OkHttpClient.Builder)

    /**
     * 创建 Retrofit Service 实例。
     *
     * @param serviceClass Retrofit Service 的类对象
     * @param baseUrl Retrofit 的基础 URL
     * @return Retrofit Service 实例
     */
    fun <S> getService(serviceClass: Class<S>, baseUrl: String): S {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(serviceClass)
    }
}