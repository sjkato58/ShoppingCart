package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.dataModels.ProductModel
import com.mtfuji.sakura.data.repositories.CartRepository
import om.mtfuji.sakura.utilities.DispatcherProvider
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