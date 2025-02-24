package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.dataModels.ProductModel

interface ReduceProductQuantityFromCartUseCase {
    suspend fun execute(productModel: ProductModel, quantity: Int = 1)
}