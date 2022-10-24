package com.edg.domain.repository

import com.edg.domain.models.products.Product
import com.edg.domain.models.products.Products
import com.edg.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FavouriteProductsRepository {
    suspend fun addFavouriteProduct(product: Product)
    suspend fun removeFavouriteProduct(id : String)
    suspend fun getAllFavouriteProducts(): Flow<Resource<List<Product>>>
}