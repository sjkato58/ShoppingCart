package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.dataModels.ProductModel

interface RemoveProductFromCartUseCase {
    suspend fun execute(productModel: ProductModel)
}