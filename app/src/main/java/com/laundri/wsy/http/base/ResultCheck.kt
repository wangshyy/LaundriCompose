package com.laundri.wsy.http.base

/**
 *  author : wsy
 *  date   : 2023/11/7
 *  desc   :
 */
inline fun <T : Any> Result<T>.checkResult(success: (T?) -> Unit, error: (String?) -> Unit) {
    if (this is Result.Success) {
        success(data)
    } else if (this is Result.Error) {
        error(exception.message)
    }
}

inline fun <T : Any> Result<T>.checkSuccess(success: (T?) -> Unit) {
    if (this is Result.Success) {
        success(data)
    }
}