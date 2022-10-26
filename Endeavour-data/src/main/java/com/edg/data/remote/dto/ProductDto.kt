package com.edg.data.remote.dto

import com.edg.domain.models.products.Product


data class ProductDto(
    val addToCartButtonText: String,
    val badges: List<String>,
    val brand: String,
    val citrusId: String,
    val id: String,
    val imageURL: String,
    val isAddToCartEnable: Boolean,
    val isDeliveryOnly: Boolean,
    val isDirectFromSupplier: Boolean,
    val isFindMeEnable: Boolean,
    val isInTrolley: Boolean,
    val isInWishlist: Boolean,
    val messages: Messages,
    val price: List<PriceDto>,
    val purchaseTypes: List<PurchaseType>,
    val ratingCount: Double,
    val saleUnitPrice: Double,
    val title: String,
    val totalReviewCount: Int
) {

    fun toProduct(): Product {
        return Product(
            imageURL = this.imageURL,
            price = this.price.map { it.toPrice() },
            ratingCount = this.ratingCount,
            title = this.title,
            brand = this.brand,
            id = this.id,
        )
    }
}