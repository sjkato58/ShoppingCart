package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.dataModels.CartItemModel
import com.mtfuji.sakura.datatest.appleProductModel
import com.mtfuji.sakura.domainmodels.discounts.AppliedDiscountModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BuyTwoGetOneFreeDiscountTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var discount: BuyTwoGetOneFreeDiscount

    companion object {
        const val DISCOUNT_NAME = "Buy 2 get 1 free Discount"
    }

    private val cartList = listOf(
        CartItemModel(
            product = appleProductModel,
            quantity = 7
        )
    )

    @BeforeEach
    fun setUp() {
        discount = BuyTwoGetOneFreeDiscount(
            productId = appleProductModel.id,
            rating = 2,
            name = DISCOUNT_NAME
        )
    }

    @Test
    fun `when a buy two get one free discount is valid then it should be applied`() =
        runTest(testDispatcher) {
            val result = discount.applyDiscount(listOf(), cartList)
            advanceUntilIdle()
            println("result: $result")
            assertTrue(result != null)
            assertEquals(1.5, result!!.discountAmount)
            assertEquals(listOf(appleProductModel.id), result.productIds)
            assertEquals(DISCOUNT_NAME, result.discountName)
            assertEquals(-1.0, result.discountPercentage)
        }

    @Test
    fun `when a product has already had a discount applied then this discount should not be applied`() =
        runTest(testDispatcher) {
            val appliedDiscountModel = AppliedDiscountModel(
                productIds = listOf(appleProductModel.id),
                discountName = BundleDiscountTest.DISCOUNT_NAME,
                discountAmount = -1.0,
                discountPercentage = -1.0
            )
            val result = discount.applyDiscount(listOf(appliedDiscountModel),  cartList)
            advanceUntilIdle()
            println("result: $result")
            assertTrue(result == null)
        }
}