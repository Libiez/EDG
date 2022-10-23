package com.edg.domain.usecase

import com.edg.domain.models.products.Product
import com.edg.domain.models.products.Products
import com.edg.domain.repository.GetProductsRepository
import com.edg.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


 class GetProductsUseCaseImpl  @Inject constructor(private val repository: GetProductsRepository)
    : GetProductsUseCase {

    override  fun getAllProducts(token: String): Flow<Resource<List<Product>>> {
        return repository.getAllProducts(token)
    }
}