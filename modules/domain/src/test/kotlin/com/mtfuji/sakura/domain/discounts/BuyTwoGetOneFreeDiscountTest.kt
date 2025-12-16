package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.dataModels.CartItemModel
import com.mtfuji.sakura.datatest.appleProductModel
import com.mtfuji.sakura.domainmodels.discounts.AppliedDiscountModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

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

    @Before
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