package com.edg.data.repository

import android.util.Log
import com.edg.data.remote.EndeavourApi
import com.edg.data.remote.dto.ProductsDto
import com.edg.domain.models.products.Product
import com.edg.domain.models.products.Products
import com.edg.domain.repository.GetProductsRepository
import com.edg.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetProductsRepositoryImpl @Inject constructor(private val api: EndeavourApi) :
    GetProductsRepository {

    override fun getAllProducts(token: String): Flow<Resource<List<Product>>> = flow {

        emit(Resource.Loading())

        try {
            var remoteWordInfo = api.getAllProducts(token)
            emit(Resource.Success(remoteWordInfo.toProducts().products))
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, Something went wrong "))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = " Couldn't reach the network, Please check you network connection"))
        }

    }

}