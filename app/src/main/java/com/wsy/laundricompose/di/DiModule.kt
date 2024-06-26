package com.wsy.laundricompose.di

import com.wsy.laundricompose.http.ApiService
import com.wsy.laundricompose.http.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *  author : wsy
 *  date   : 2024/6/26
 *  desc   : 依赖注入模块 ( hilt )
 */

// 这个注解指示 Hilt 在 SingletonComponent 生命周期中进行依赖注入
// SingletonComponent 是 Hilt 的一个预定义组件，用于提供应用级别的单例对象
@InstallIn(SingletonComponent::class)
// 这个注解表示它是一个 Hilt 模块，用于提供依赖注入所需的对象实例
@Module
object DiModule {
    /**
     * 提供 RetrofitClient 的单例实例。
     *
     * @return RetrofitClient 实例
     */
    @Singleton
    // 这个注解用于告诉 Hilt 如何创建或提供特定类型的对象实例
    @Provides
    fun provideRetrofitClient(): RetrofitClient {
        return RetrofitClient()
    }

    /**
     * 提供 ApiService 的单例实例。
     *
     * @param retrofitClient 用于创建 ApiService 的 RetrofitClient 实例。
     * @return ApiService 实例
     */
    @Singleton
    @Provides
    fun provideApiService(retrofitClient: RetrofitClient): ApiService {
        return retrofitClient.service
    }
}