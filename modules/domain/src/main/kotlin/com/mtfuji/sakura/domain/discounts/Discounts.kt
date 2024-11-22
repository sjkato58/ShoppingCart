package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.dataModels.CartItemModel
import com.mtfuji.sakura.domainmodels.discounts.AppliedDiscountModel

interface Discounts {
    val rating: Int
    val name: String
    fun applyDiscount(
        appliedDiscounts: List<AppliedDiscountModel>,
        cartItemList: List<CartItemModel>
    ): AppliedDiscountModel?
}