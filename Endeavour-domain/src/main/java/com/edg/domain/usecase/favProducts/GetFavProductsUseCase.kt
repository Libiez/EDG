package com.edg.domain.usecase.favProducts

import com.edg.domain.models.products.Product
import com.edg.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetFavProductsUseCase {
    suspend fun addFavouriteProduct(product: Product)
    suspend fun removeFavouriteProduct(id : String)
    suspend fun getAllFavouriteProducts(): Flow<Resource<List<Product>>>
    suspend fun isProductExist(id: String): Boolean
}