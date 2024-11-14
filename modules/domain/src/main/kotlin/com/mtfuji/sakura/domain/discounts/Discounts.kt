package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domain.models.AppliedDiscountModel
import com.mtfuji.sakura.domain.models.CartItemModel

interface Discounts {
    val rating: Int
    val name: String
    fun applyDiscount(
        appliedDiscounts: List<AppliedDiscountModel>,
        cartItemList: List<CartItemModel>
    ): AppliedDiscountModel?
}