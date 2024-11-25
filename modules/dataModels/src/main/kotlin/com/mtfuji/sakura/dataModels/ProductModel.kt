package com.mtfuji.sakura.dataModels

import kotlinx.serialization.Serializable

@Serializable
data class ProductModel(
    val id: String,
    val name: String,
    val price: Double,
    val description: String,
    val imageUrl: String,
    val category: String
)
