package com.edg.data.remote.dto

import com.edg.domain.models.products.Price


data class PriceDto(
    val isOfferPrice: Boolean,
    val message: String,
    val value: Double
) {

    fun toPrice(): Price {
        return Price(
            isOfferPrice = this.isOfferPrice,
            value = this.value
        )
    }
}