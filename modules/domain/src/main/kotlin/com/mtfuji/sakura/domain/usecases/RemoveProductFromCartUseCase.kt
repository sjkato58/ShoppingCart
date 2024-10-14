package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.domain.models.ProductModel

interface RemoveProductFromCartUseCase {
    suspend fun execute(productModel: ProductModel)
}