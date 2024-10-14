package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.domain.discounts.DiscountManager
import com.mtfuji.sakura.domain.models.CheckOutPricesModel
import com.mtfuji.sakura.domain.utils.DispatcherProvider
import kotlinx.coroutines.withContext

class CheckoutUseCaseImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val discountManager: DiscountManager
): CheckoutUseCase {
    override suspend fun execute(): CheckOutPricesModel = withContext(dispatcherProvider.io) {
        discountManager.calculateTotalDiscount()
    }
}