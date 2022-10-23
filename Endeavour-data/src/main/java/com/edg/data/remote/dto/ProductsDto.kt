package com.edg.data.remote.dto

import com.edg.domain.models.products.Products


data class ProductsDto(val products: List<ProductDto>) {

    fun toProducts(): Products {
        return Products(products = products.map { it.toProduct()})
    }

}