package com.edg.domain.models.products

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Price(
    val isOfferPrice: Boolean,
    val value: Double
) : Parcelable