package com.edg.data.remote.dto

import retrofit2.http.GET

interface ProductAPI {

    @GET("v3/2f06b453-8375-43cf-861a-06e95a951328")
    suspend fun getProducts(): List<ProductsDto>

}