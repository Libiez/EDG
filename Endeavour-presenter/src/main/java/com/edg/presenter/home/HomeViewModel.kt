package com.edg.presenter.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edg.domain.usecase.GetProductsUseCase
import com.edg.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val productsUseCase: GetProductsUseCase) :
    ViewModel() {

    val state = MutableStateFlow<Resource>(Resource.START)

    fun getProducts() {

        viewModelScope.launch() {

            productsUseCase.getAllProducts(token = "2f06b453-8375-43cf-861a-06e95a951328")
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            Log.d(TAG, "getProducts: ")
                        }
                        is Resource.Error -> {
                            Log.d(TAG, "getProducts: ")
                        }
                        is Resource.Loading -> {
                            Log.d(TAG, "getProducts: ")
                        }
                    }
                }.launchIn(this)

        }
    }
}