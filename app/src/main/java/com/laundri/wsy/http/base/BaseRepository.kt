package com.laundri.wsy.http.base

import com.google.gson.Gson
import com.laundri.wsy.ext.logI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

/**
 *  author : wsy
 *  date   : 2023/11/7
 *  desc   :
 */
open class BaseRepository {
    private val TAG = this::class.java.simpleName
    protected val mediaType: MediaType = "application/json;charset=utf-8".toMediaType()
    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Result<T>,
        errorMessage: String = "网络错误"
    ): Result<T> {
        return try {
            call()
        } catch (e: Exception) {
            if (e is HttpException) {
                val errorBodyStr = (e.response()?.errorBody() as ResponseBody).string()
                "errorBody：$errorBodyStr".logI("okhttp")
                val errorResponse: Response<*> = Gson().fromJson(
                    errorBodyStr,
                    Response::class.java
                )
                "errorMessage：${errorResponse.note}".logI("okhttp")
                Result.Error(IOException(errorResponse.note))
            } else {
                "errorMessage：${e.message}".logI("okhttp")
                Result.Error(IOException(e.message))
            }
        }
    }

    suspend fun <T : Any> executeResponse(
        response: Response<T>,
        successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): Result<T> {
        return coroutineScope {
            when (response.code) {
                200 -> {
                    successBlock?.let { it() }
                    Result.Success(response.data)
                }
                else -> {
                    errorBlock?.let { it() }
                    Result.Error(IOException(response.note))
                }
            }
        }
    }
}