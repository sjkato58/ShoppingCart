package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domain.dummyData.appleProductModel
import com.mtfuji.sakura.domain.dummyData.bananaProductModel
import com.mtfuji.sakura.domain.dummyData.buyTwoGetOneFreeDiscount
import com.mtfuji.sakura.domain.dummyData.carrotProductModel
import com.mtfuji.sakura.domain.models.AppliedDiscountModel
import com.mtfuji.sakura.domain.models.CartItemModel
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

    companion object {
        const val DISCOUNT_NAME = "Cart Discount"
    }

    private val cartList = listOf(
        CartItemModel(
            product = appleProductModel,
            quantity = 4
        ),
        CartItemModel(
            product = bananaProductModel,
            quantity = 6
        ),
        CartItemModel(
            product = carrotProductModel,
            quantity = 2
        )
    )

    @BeforeEach
    fun setUp() {
        discount = PercentageOffCartDiscount(
            percentage = 25.0,
            rating = 0,
            name = DISCOUNT_NAME
        )
    }

    @Test
    fun `when a cart percentage discount is valid then it should be applied`() =
        runTest(testDispatcher) {
            val result = discount.applyDiscount(listOf(), cartList)
            advanceUntilIdle()
            println("result: $result")
            assertTrue(result != null)
            assertEquals(DISCOUNT_NAME, result!!.discountName)
            assertEquals(25.00, result.discountPercentage)
        }

    @Test
    fun `when the cart percentage discount is not usable then it should not be applied`() =
        runTest(testDispatcher) {
            val appliedDiscountModel = AppliedDiscountModel(
                productIds = listOf(appleProductModel.id),
                discountName = buyTwoGetOneFreeDiscount.name,
                discountAmount = 2.0,
                discountPercentage = -1.0
            )
            val result = discount.applyDiscount(listOf(appliedDiscountModel), cartList)
            advanceUntilIdle()
            println("result: $result")
            assertTrue(result == null)
        }
}