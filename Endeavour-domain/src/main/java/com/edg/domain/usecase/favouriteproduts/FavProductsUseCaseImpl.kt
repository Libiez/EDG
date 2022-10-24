package com.edg.domain.usecase.favouriteproduts

import com.edg.domain.models.products.Product
import com.edg.domain.repository.FavouriteProductsRepository
import com.edg.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FavProductsUseCaseImpl @Inject constructor(private val repository: FavouriteProductsRepository) :
    GetFavProductsUseCase {
    override suspend fun addFavouriteProduct(product: Product) {
        repository.addFavouriteProduct(product)
    }

    override suspend fun removeFavouriteProduct(id: String) {
        repository.removeFavouriteProduct(id)
    }

    override suspend fun getAllFavouriteProducts(): Flow<Resource<List<Product>>> {
        return repository.getAllFavouriteProducts()
    }


}