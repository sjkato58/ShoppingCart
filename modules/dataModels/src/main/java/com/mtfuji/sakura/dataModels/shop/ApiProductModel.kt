package com.mtfuji.sakura.dataModels.shop

import kotlinx.serialization.Serializable

@Serializable
data class ApiProductModel(
    val id: String,
    val name: String,
    val price: Double,
    val description: String,
    val imageUrl: String,
    val category: String
)
