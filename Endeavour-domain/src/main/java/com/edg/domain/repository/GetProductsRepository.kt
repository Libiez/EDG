package com.edg.domain.repository

import com.edg.domain.models.products.Product
import com.edg.domain.models.products.Products
import com.edg.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetProductsRepository {

    fun getAllProducts(token: String): Flow<Resource<List<Product>>>

}