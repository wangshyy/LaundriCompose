package com.wsy.laundricompose.http.base

/**
 *  author : wsy
 *  date   : 2023/11/7
 *  desc   :
 */
sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T?) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}