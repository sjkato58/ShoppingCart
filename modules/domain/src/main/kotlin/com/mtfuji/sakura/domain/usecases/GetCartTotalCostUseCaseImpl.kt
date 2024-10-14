package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.domain.repositories.CartRepository
import com.mtfuji.sakura.domain.utils.DispatcherProvider
import kotlinx.coroutines.withContext

class GetCartTotalCostUseCaseImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val cartRepository: CartRepository
): GetCartTotalCostUseCase {
    override suspend fun execute(): Double = withContext(dispatcherProvider.io) {
        cartRepository.getTotalCost()
    }
}