package com.mtfuji.sakura.dataModels.cart

import com.mtfuji.sakura.dataModels.shop.ApiProductModel

data class ApiCartItemModel(
    val product: ApiProductModel,
    var quantity: Int
)
