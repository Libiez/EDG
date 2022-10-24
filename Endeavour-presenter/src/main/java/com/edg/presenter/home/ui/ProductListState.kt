package com.edg.presenter.home.ui

import com.edg.domain.models.products.Product
import com.edg.domain.models.products.Products

data class ProductListState(

    val  productsItems: List<Product> = emptyList(),
    val isLoading: Boolean = false
)
