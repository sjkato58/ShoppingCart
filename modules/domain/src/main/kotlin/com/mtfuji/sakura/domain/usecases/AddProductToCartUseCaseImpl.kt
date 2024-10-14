package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.domain.models.ProductModel
import com.mtfuji.sakura.domain.repositories.CartRepository
import com.mtfuji.sakura.domain.utils.DispatcherProvider
import kotlinx.coroutines.withContext

class AddProductToCartUseCaseImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val cartRepository: CartRepository
): AddProductToCartUseCase {
    override suspend fun execute(productModel: ProductModel, quantity: Int) {
        withContext(dispatcherProvider.io) {
            cartRepository.addProduct(productModel, quantity)
        }
    }
}