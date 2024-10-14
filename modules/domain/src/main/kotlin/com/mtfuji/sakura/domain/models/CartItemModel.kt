package com.mtfuji.sakura.domain.models

data class CartItemModel(
    val product: ProductModel,
    var quantity: Int
)
