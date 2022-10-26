package com.edg.presenter.product

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edg.domain.models.products.Product
import com.edg.domain.usecase.favProducts.GetFavProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(private val useCase: GetFavProductsUseCase) :
    ViewModel() {

    var stateVariable by mutableStateOf(false)
        private set

    fun isProductExist(id: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                useCase.isProductExist(id)
            }
            stateVariable = result

        }
    }

    fun addFavouriteProduct(product: Product) {
        viewModelScope.launch {

            withContext(Dispatchers.IO) {
                useCase.addFavouriteProduct(product)
            }
            isProductExist(product.id)
        }
    }

    fun removeFavouriteProduct(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                useCase.removeFavouriteProduct(id)
            }
            isProductExist(id)
        }

    }


}