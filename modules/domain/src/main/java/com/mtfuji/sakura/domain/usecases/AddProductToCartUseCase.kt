package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.dataModels.ProductModel

interface AddProductToCartUseCase {
    suspend fun execute(productModel: ProductModel, quantity: Int)
}