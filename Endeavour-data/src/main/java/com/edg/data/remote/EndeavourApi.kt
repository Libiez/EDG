package com.edg.data.remote


import com.edg.data.remote.dto.ProductDto
import com.edg.data.remote.dto.ProductsDto
import retrofit2.http.GET
import retrofit2.http.Path

  interface EndeavourApi {

    @GET("v3/{token}")
    suspend fun getAllProducts(@Path("token") token: String): ProductsDto

}