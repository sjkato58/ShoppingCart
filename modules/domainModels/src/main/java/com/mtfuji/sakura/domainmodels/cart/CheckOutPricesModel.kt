package com.mtfuji.sakura.domainmodels.cart

import com.mtfuji.sakura.domainmodels.discounts.AppliedDiscountModel

data class CheckOutPricesModel(
    val finalTotal: Double,
    val totalDiscount: Double,
    val appliedDiscounts: List<AppliedDiscountModel>
)
