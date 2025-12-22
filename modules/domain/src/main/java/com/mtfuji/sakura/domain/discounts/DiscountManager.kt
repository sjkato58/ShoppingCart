package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domainmodels.cart.CheckOutPricesModel

interface DiscountManager {
    suspend fun calculateTotalDiscount(): CheckOutPricesModel
}