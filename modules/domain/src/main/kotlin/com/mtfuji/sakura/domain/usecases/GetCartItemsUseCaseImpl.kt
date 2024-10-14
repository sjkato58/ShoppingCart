package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.domain.models.CartItemModel
import com.mtfuji.sakura.domain.repositories.CartRepository
import com.mtfuji.sakura.domain.utils.DispatcherProvider
import kotlinx.coroutines.withContext

class GetCartItemsUseCaseImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val cartRepository: CartRepository
): GetCartItemsUseCase {
    override suspend fun execute(): List<CartItemModel> = withContext(dispatcherProvider.io) {
        cartRepository.getItems()
    }
}