package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.domain.models.ProductModel
import com.mtfuji.sakura.domain.repositories.CartRepository
import com.mtfuji.sakura.domain.utils.DispatcherProvider
import kotlinx.coroutines.withContext

class RemoveProductFromCartUseCaseImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val cartRepository: CartRepository
): RemoveProductFromCartUseCase {
    override suspend fun execute(productModel: ProductModel) {
        withContext(dispatcherProvider.io) {
            cartRepository.removeProduct(productModel)
        }
    }
}