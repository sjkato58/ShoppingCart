package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.dataModels.ProductModel
import com.mtfuji.sakura.data.repositories.CartRepository
import com.mtfuji.sakura.utilities.DispatcherProvider
import kotlinx.coroutines.withContext

class ReduceProductQuantityFromCartUseCaseImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val cartRepository: CartRepository
): ReduceProductQuantityFromCartUseCase {
    override suspend fun execute(productModel: ProductModel, quantity: Int) {
        withContext(dispatcherProvider.io) {
            cartRepository.reduceProductQuantity(productModel, quantity)
        }
    }
}