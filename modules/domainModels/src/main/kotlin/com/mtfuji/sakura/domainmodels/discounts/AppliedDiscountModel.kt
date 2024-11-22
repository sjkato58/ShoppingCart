package com.mtfuji.sakura.domainmodels.discounts

data class AppliedDiscountModel(
    val productIds: List<String>,
    val discountName: String,
    val discountAmount: Double,
    val discountPercentage: Double
)