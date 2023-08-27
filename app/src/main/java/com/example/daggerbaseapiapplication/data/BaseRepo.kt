package com.example.daggerbaseapiapplication.data

import com.example.daggerbaseapiapplication.model.Product
import com.example.daggerbaseapiapplication.utils.Utils
import com.example.swipemvvmkoin.util.AppResult
import javax.inject.Inject

// @Inject tells Dagger how to provide instances of this type
class BaseRepo @Inject constructor(private val baseApi: BaseApi) {

    suspend fun getProducts(): AppResult<List<Product>> {
        return try {
            val result = baseApi.getProducts()
            if (result.isSuccessful && result.body() != null) {
                Utils.handleSuccess(result)
            } else {
                Utils.handleApiError(result)
            }
        } catch (e: Exception) {
            AppResult.Error(e)
        }
    }
}