package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.dataModels.ProductModel
import com.mtfuji.sakura.data.repositories.CartRepository
import om.mtfuji.sakura.utilities.DispatcherProvider
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