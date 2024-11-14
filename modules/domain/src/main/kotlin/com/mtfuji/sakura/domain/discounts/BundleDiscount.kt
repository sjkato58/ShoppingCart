package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domain.models.AppliedDiscountModel
import com.mtfuji.sakura.domain.models.CartItemModel

class BundleDiscount(
    val productIds: List<String>,
    private val bundlePrice: Double,
    override val rating: Int,
    override val name: String
): Discounts {

    override fun applyDiscount(
        appliedDiscounts: List<AppliedDiscountModel>,
        cartItemList: List<CartItemModel>
    ): AppliedDiscountModel? {
        val discountedProductIds = appliedDiscounts.obtainProductIds()

        val eligibleCartItems = cartItemList.fetchEligibleCartItems(discountedProductIds)

        val cartItemsMap = eligibleCartItems.associateBy { it.product.id }

        val discountAmount = calculateDiscount(cartItemsMap)
        return if (discountAmount > 0) {
            AppliedDiscountModel(
                productIds = productIds,
                discountName = name,
                discountAmount = discountAmount,
                discountPercentage = -1.0
            )
        } else null
    }

    private fun List<CartItemModel>.fetchEligibleCartItems(
        discountedProductIds: Set<String>
    ): List<CartItemModel> = this.filterNot { discountedProductIds.contains(it.product.id) }

    private fun calculateDiscount(cartItemsMap: Map<String, CartItemModel>): Double {
        val numberOfSets = productIds.minOfOrNull { productId ->
            cartItemsMap[productId]?.quantity ?: 0
        } ?: 0

        if (numberOfSets <= 0) return 0.0

        val totalBundlePrice = productIds.sumOf { productId ->
            cartItemsMap[productId]?.let { it.product.price * numberOfSets } ?: 0.0
        }
        val discountAmount = totalBundlePrice - (bundlePrice * numberOfSets)
        return if (discountAmount > 0) discountAmount else 0.0
    }
}