package com.mtfuji.sakura.domainmodels.shop

data class ProductModel(
    val id: String,
    val name: String,
    val price: Double,
    val description: String,
    val imageUrl: String,
    val category: String
)
