package com.mtfuji.sakura.shoppingcart.di

import com.mtfuji.sakura.domain.discounts.BundleDiscount
import com.mtfuji.sakura.domain.discounts.BuyTwoGetOneFreeDiscount
import com.mtfuji.sakura.domain.discounts.DiscountManager
import com.mtfuji.sakura.domain.discounts.DiscountManagerImpl
import com.mtfuji.sakura.domain.discounts.Discounts
import com.mtfuji.sakura.domain.discounts.PercentageOffCartDiscount
import com.mtfuji.sakura.domain.discounts.SpecificProductDiscount
import org.koin.dsl.bind
import org.koin.dsl.module

val discountsModule = module {
    single {
        BundleDiscount(
            productIds = listOf(
                "12345",
                "34512",
                "45123",
            ),
            bundlePrice = 3.0,
            rating = 3,
            name = "Meal Bundle Discount"
        )
    } bind Discounts::class
    single {
        BuyTwoGetOneFreeDiscount(
            productId = "12345",
            rating = 2,
            name = "Buy 2 get 1 free Discount"
        )
    } bind Discounts::class
    single {
        PercentageOffCartDiscount(
            percentage = 25.0,
            rating = 0,
            name = "Cart Discount"
        )
    } bind Discounts::class
    single {
        SpecificProductDiscount(
            productId = "34512",
            discountPercentage = 40.0,
            rating = 1,
            name = "Specific Product Discount"
        )
    } bind Discounts::class
    single { DiscountManagerImpl(get(), getAll(), get()) } bind DiscountManager::class
}