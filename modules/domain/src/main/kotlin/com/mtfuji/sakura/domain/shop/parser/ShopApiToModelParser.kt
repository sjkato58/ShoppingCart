package com.mtfuji.sakura.domain.shop.parser

import com.mtfuji.sakura.dataModels.shop.ApiProductModel
import com.mtfuji.sakura.domainmodels.shop.ProductModel

fun List<ApiProductModel>.toModel(): List<ProductModel> {
    return this.map {
        ProductModel(
            id = it.id,
            name = it.name,
            price = it.price,
            description = it.description,
            imageUrl = it.imageUrl,
            category = it.category
        )
    }
}