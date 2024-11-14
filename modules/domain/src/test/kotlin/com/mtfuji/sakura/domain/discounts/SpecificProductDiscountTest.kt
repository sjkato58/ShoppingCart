package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domain.dummyData.seaSaltStrollerProductModel
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
class SpecificProductDiscountTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var discount: SpecificProductDiscount

    companion object {
        const val DISCOUNT_NAME = "Specific Product Discount"
    }

    private val cartList = listOf(
        CartItemModel(
            product = seaSaltStrollerProductModel,
            quantity = 4
        )
    )

    @BeforeEach
    fun setUp() {
        discount = SpecificProductDiscount(
            productId = seaSaltStrollerProductModel.id,
            discountPercentage = 40.0,
            rating = 1,
            name = DISCOUNT_NAME
        )
    }

    @Test
    fun `when a specific product discount is valid then it should be applied`() =
        runTest(testDispatcher) {
            val result = discount.applyDiscount(listOf(), cartList)
            advanceUntilIdle()
            println("result: $result")
            assertTrue(result != null)
            assertEquals(1.6, result!!.discountAmount)
            assertEquals(listOf(seaSaltStrollerProductModel.id), result.productIds)
            assertEquals(DISCOUNT_NAME, result.discountName)
            assertEquals(-1.0, result.discountPercentage)
        }

    @Test
    fun `when a product has already had a discount applied then this discount should not be applied`() =
        runTest(testDispatcher) {
            val appliedDiscountModel = AppliedDiscountModel(
                productIds = listOf(seaSaltStrollerProductModel.id),
                discountName = DISCOUNT_NAME,
                discountAmount = -1.0,
                discountPercentage = -1.0
            )
            val result = discount.applyDiscount(listOf(appliedDiscountModel),  cartList)
            advanceUntilIdle()
            println("result: $result")
            assertTrue(result == null)
        }
}