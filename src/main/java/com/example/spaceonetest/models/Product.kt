package com.example.spaceonetest.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id : String,
    var name: String = "",
    var weight : Double = 0.0,
    var category : String = "",
) : Parcelable


data class ProductDTO(
    var name: String = "",
    var weight : Double = 0.0,
    var category : String = "",
)
