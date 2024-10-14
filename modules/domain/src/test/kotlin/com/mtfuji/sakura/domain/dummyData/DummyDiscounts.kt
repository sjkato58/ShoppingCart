package com.mtfuji.sakura.domain.dummyData

import com.mtfuji.sakura.domain.discounts.BundleDiscount
import com.mtfuji.sakura.domain.discounts.BuyTwoGetOneFreeDiscount
import com.mtfuji.sakura.domain.discounts.PercentageOffCartDiscount
import com.mtfuji.sakura.domain.discounts.SpecificProductDiscount

val bundleDiscount = BundleDiscount(
    productIds = listOf(
        bananaProductModel.id,
        seaSaltStrollerProductModel.id,
        baconLettuceTomatoProductModel.id
    ),
    bundlePrice = 3.0,
    rating = 3,
    name = "Meal Bundle Discount"
)

val buyTwoGetOneFreeDiscount = BuyTwoGetOneFreeDiscount(
    productId = appleProductModel.id,
    rating = 2,
    name = "Buy 2 get 1 free Discount"
)

val percentageOffCartDiscount = PercentageOffCartDiscount(
    percentage = 25.0,
    rating = 0,
    name = "Cart Discount"
)

val specificProductDiscount = SpecificProductDiscount(
    productId = seaSaltStrollerProductModel.id,
    discountPercentage = 40.0,
    rating = 1,
    name = "Specific Product Discount"
)