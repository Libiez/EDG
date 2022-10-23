package com.edg.domain.models.products

data class Product(
    val imageURL: String,
    val price: List<Price>,
    val ratingCount: Double,
    val title: String,
) {
}