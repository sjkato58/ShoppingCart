package com.mtfuji.sakura.domain.models

data class AppliedDiscountModel(
    val productId: String,
    val discountName: String,
    val discountAmount: Double
)
