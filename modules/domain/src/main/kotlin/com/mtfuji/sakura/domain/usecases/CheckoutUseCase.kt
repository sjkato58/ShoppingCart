package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.domainmodels.cart.CheckOutPricesModel

interface CheckoutUseCase {
    suspend fun execute(): CheckOutPricesModel
}