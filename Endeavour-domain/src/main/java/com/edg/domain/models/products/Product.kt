package com.edg.domain.models.products

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Product (
    val imageURL: String,
    val price: List<Price>,
    val ratingCount: Double,
    val title: String,
    val brand: String,
    val id: String
) : Parcelable