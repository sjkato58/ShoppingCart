package com.mtfuji.sakura.domain.models

data class AppliedDiscountModel(
    val productIds: List<String>,
    val discountName: String,
    val discountAmount: Double,
    val discountPercentage: Double
)