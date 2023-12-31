package com.example.swipemvvmkoin.util

import retrofit2.Response
import java.lang.Exception

sealed class AppResult<out T> {

    data class Success<out T>(val successData: T) : AppResult<T>()

    class Error(val exception: Exception, val message: String? = exception.message) :
        AppResult<Nothing>()

    fun <T : Any> handleApiError(resp: Response<T>): AppResult.Error {
        val error = ApiErrorUtils.parseError(resp)
        return AppResult.Error(Exception(error.message))
    }

    fun <T : Any> handleSuccess(response: Response<T>): AppResult<T> {
        response.body()?.let {
            return AppResult.Success(it)
        } ?: return handleApiError(response)
    }
}