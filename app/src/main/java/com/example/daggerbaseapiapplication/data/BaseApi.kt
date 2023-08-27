package com.example.daggerbaseapiapplication.data

import com.example.daggerbaseapiapplication.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface BaseApi {

    @GET("products")
    suspend fun getProducts(): Response<List<Product>>

}