package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.domain.discounts.BundleDiscount
import com.mtfuji.sakura.domain.discounts.BuyTwoGetOneFreeDiscount
import com.mtfuji.sakura.domain.discounts.Discounts
import com.mtfuji.sakura.domain.discounts.PercentageOffCartDiscount
import com.mtfuji.sakura.domain.discounts.SpecificProductDiscount

class GetDiscountsUseCaseImpl(
    private val bundleDiscount: BundleDiscount,
    private val buyTwoGetOneFreeDiscount: BuyTwoGetOneFreeDiscount,
    private val percentageOffCartDiscount: PercentageOffCartDiscount,
    private val specificProductDiscount: SpecificProductDiscount
): GetDiscountsUseCase {
    override fun execute(): List<Discounts> = listOf(
        bundleDiscount, buyTwoGetOneFreeDiscount, percentageOffCartDiscount, specificProductDiscount
    )
}