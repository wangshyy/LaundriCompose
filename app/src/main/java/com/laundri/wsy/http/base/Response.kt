package com.laundri.wsy.http.base

/**
 *  author : wsy
 *  date   : 2023/11/7
 *  desc   :
 */
data class Response<out T>(
    val code: Int?,
    val msg: String?,
    val note: String?,
    val data: T
)
