package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.dataModels.CartItemModel
import com.mtfuji.sakura.data.repositories.CartRepository
import om.mtfuji.sakura.utilities.DispatcherProvider
import kotlinx.coroutines.withContext

class GetCartItemsUseCaseImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val cartRepository: CartRepository
): GetCartItemsUseCase {
    override suspend fun execute(): List<CartItemModel> = withContext(dispatcherProvider.io) {
        cartRepository.getItems()
    }
}