package com.example.daggerbaseapiapplication.utils

import com.example.swipemvvmkoin.util.AppResult
import retrofit2.Response

object Utils {
    fun <T : Any> handleApiError(resp: Response<T>): AppResult.Error {
        return AppResult.Error(Exception(resp.message()))
    }

    fun <T : Any> handleSuccess(response: Response<T>): AppResult<T> {
        response.body()?.let {
            return AppResult.Success(it)
        } ?: return handleApiError(response)
    }
}