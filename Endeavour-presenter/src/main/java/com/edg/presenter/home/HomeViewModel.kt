package com.edg.presenter.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edg.domain.models.products.Product
import com.edg.domain.usecase.favProducts.GetFavProductsUseCase
import com.edg.domain.usecase.getproducts.GetProductsUseCase
import com.edg.domain.utils.Resource
import com.edg.presenter.home.compose.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productsUseCase: GetProductsUseCase,
    private val favProductsUseCase: GetFavProductsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ProductListState())
    val state: State<ProductListState> = _state

    private val _stateFavProduct = mutableStateOf(ProductListState())
    val stateFavProduct: State<ProductListState> = _stateFavProduct

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getAllProducts("2f06b453-8375-43cf-861a-06e95a951328")
    }


    private fun getAllProducts(categoryId: String) {

        viewModelScope.launch {

            productsUseCase.getAllProducts(token = categoryId)
                .onEach { result ->

                    when (result) {

                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                productsItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }

                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                productsItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(
                                UIEvent.ShowSnack(
                                    result.message ?: "Unknown error"
                                )
                            )
                        }

                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                productsItems = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }

                    }

                }.launchIn(this)


        }
    }

    fun addFavouriteProduct(product: Product) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                favProductsUseCase.addFavouriteProduct(product)
            }

            isProductExist(product.id)
        }
    }
    private fun isProductExist(id: String) {
        viewModelScope.launch {
                favProductsUseCase.isProductExist(id)
        }
    }

    fun removeFavouriteProduct(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                favProductsUseCase.removeFavouriteProduct(id)
            }
            getAllFavouriteProducts()
        }
    }

    fun getAllFavouriteProducts() {

        viewModelScope.launch {
            favProductsUseCase.getAllFavouriteProducts()
                .onEach { result ->

                    when (result) {

                        is Resource.Success -> {
                            _stateFavProduct.value = stateFavProduct.value.copy(
                                productsItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }

                        is Resource.Error -> {
                            _stateFavProduct.value = stateFavProduct.value.copy(
                                productsItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(
                                UIEvent.ShowSnack(
                                    result.message ?: "Unknown error"
                                )
                            )
                        }

                        is Resource.Loading -> {
                            _stateFavProduct.value = stateFavProduct.value.copy(
                                productsItems = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }

                    }

                }.launchIn(this)


        }
    }


    sealed class UIEvent {
        data class ShowSnack(val message: String) : UIEvent()
    }

}