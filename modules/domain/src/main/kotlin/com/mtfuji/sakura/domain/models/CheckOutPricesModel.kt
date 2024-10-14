package com.mtfuji.sakura.domain.models

data class CheckOutPricesModel(
    val finalTotal: Double,
    val totalDiscount: Double,
    val appliedDiscounts: List<AppliedDiscountModel>
)
