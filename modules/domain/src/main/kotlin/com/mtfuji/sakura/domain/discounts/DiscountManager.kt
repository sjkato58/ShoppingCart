package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domain.models.CheckOutPricesModel

interface DiscountManager {
    suspend fun calculateTotalDiscount(): CheckOutPricesModel
}