package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.dataModels.CartItemModel

interface GetCartItemsUseCase {
    suspend fun execute(): List<CartItemModel>
}