package com.edg.domain.usecase.getproducts

import com.edg.domain.models.products.Product
import com.edg.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetProductsUseCase {

    fun getAllProducts(token: String): Flow<Resource<List<Product>>>

}