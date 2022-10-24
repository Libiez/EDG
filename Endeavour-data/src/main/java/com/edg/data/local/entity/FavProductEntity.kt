package com.edg.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edg.domain.models.products.Price
import com.edg.domain.models.products.Product

@Entity
data class FavProductEntity(
    val imageURL: String,
    val price: List<Price>,
    val ratingCount: Double,
    val title: String,
    val brand: String,
    @PrimaryKey val id: String
) {

    companion object {
        fun toFavProductEntity(product: Product) = FavProductEntity(
            imageURL = product.imageURL,
            price = product.price,
            ratingCount = product.ratingCount,
            title = product.title,
            brand = product.brand,
            id = product.id,
        )
    }

    fun toProduct() = Product(
        imageURL = imageURL,
        price = price,
        ratingCount = ratingCount,
        title = title,
        brand = brand,
        id = id)


}

