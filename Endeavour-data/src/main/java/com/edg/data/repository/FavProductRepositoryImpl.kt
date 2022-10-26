package com.edg.data.repository

import com.edg.data.local.FavProductDao
import com.edg.data.local.entity.FavProductEntity.Companion.toFavProductEntity
import com.edg.domain.models.products.Product
import com.edg.domain.repository.FavouriteProductsRepository
import com.edg.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavProductRepositoryImpl @Inject constructor(private val dao: FavProductDao) :
    FavouriteProductsRepository {
    override suspend fun addFavouriteProduct(product: Product) {
        dao.addFavouriteProduct(toFavProductEntity(product))
    }

    override suspend fun removeFavouriteProduct(id: String) {
        dao.removeFavouriteProduct(id)
    }

    override suspend fun getAllFavouriteProducts(): Flow<Resource<List<Product>>> = flow {
        val favProducts = dao.getFavouriteProducts().map { it.toProduct() }
        emit(Resource.Success(favProducts))
    }

    override suspend fun isProductExist(id: String): Boolean {
        return dao.isProductExist(id)
    }


}


