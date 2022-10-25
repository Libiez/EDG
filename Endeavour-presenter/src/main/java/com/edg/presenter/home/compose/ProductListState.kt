package com.edg.presenter.home.compose

import com.edg.domain.models.products.Product

data class ProductListState(

    val  productsItems: List<Product> = emptyList(),
    val isLoading: Boolean = false
)
