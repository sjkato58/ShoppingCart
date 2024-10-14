package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domain.dummyData.appleProductModel
import com.mtfuji.sakura.domain.dummyData.bananaProductModel
import com.mtfuji.sakura.domain.dummyData.buyTwoGetOneFreeDiscount
import com.mtfuji.sakura.domain.dummyData.carrotProductModel
import com.mtfuji.sakura.domain.models.AppliedDiscountModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PercentageOffCartDiscountTest {
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var discount: PercentageOffCartDiscount

    @BeforeEach
    fun setUp() {
        discount = PercentageOffCartDiscount(
            percentage = 25.0,
            rating = 0,
            name = "Cart Discount"
        )
    }

    @Test
    fun `when a cart percentage discount is valid then it should be applied`() =
        runTest(testDispatcher) {
            val total = (appleProductModel.price * 4) + (bananaProductModel.price * 6) + (carrotProductModel.price * 2)
            val result = discount.applyToTotal(total)
            advanceUntilIdle()
            assertEquals(2.75, result)
        }

    @Test
    fun `when the cart percentage discount is not usable then it should not be applied`() =
        runTest(testDispatcher) {
            val list = listOf(
                AppliedDiscountModel(
                    productId = appleProductModel.id,
                    discountName = buyTwoGetOneFreeDiscount.name,
                    discountAmount = 2.0
                )
            )
            val result = discount.canApplyDiscount(list)
            advanceUntilIdle()
            assertFalse(result)
        }
}