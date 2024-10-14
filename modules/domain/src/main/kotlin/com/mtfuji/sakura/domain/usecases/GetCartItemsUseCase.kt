package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.domain.models.CartItemModel

interface GetCartItemsUseCase {
    suspend fun execute(): List<CartItemModel>
}