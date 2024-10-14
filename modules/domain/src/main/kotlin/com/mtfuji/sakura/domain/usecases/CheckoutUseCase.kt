package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.domain.models.CheckOutPricesModel

interface CheckoutUseCase {
    suspend fun execute(): CheckOutPricesModel
}