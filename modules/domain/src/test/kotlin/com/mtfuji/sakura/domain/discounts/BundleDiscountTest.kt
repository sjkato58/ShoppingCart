package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domain.dummyData.baconLettuceTomatoProductModel
import com.mtfuji.sakura.domain.dummyData.bananaProductModel
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
class BundleDiscountTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var discount: BundleDiscount

    companion object {
        const val DISCOUNT_NAME = "Meal Bundle Discount"
    }

    private val cartList = listOf(
        CartItemModel(
            product = bananaProductModel,
            quantity = 3
        ),
        CartItemModel(
            product = baconLettuceTomatoProductModel,
            quantity = 3
        ),
        CartItemModel(
            product = seaSaltStrollerProductModel,
            quantity = 2
        )
    )

    @BeforeEach
    fun setUp() {
        discount = BundleDiscount(
            productIds = listOf(
                bananaProductModel.id,
                baconLettuceTomatoProductModel.id,
                seaSaltStrollerProductModel.id
            ),
            bundlePrice = 3.0,
            rating = 3,
            name = DISCOUNT_NAME
        )
    }

    @Test
    fun `when a bundle discount is valid then it should be applied`() =
        runTest(testDispatcher) {
            val result = discount.applyDiscount(listOf(),  cartList)
            advanceUntilIdle()
            println("result: $result")
            val productIdList = listOf(
                bananaProductModel.id,
                baconLettuceTomatoProductModel.id,
                seaSaltStrollerProductModel.id
            )
            assertTrue(result != null)
            assertEquals(3.5, result!!.discountAmount)
            assertEquals(productIdList, result.productIds)
            assertEquals(DISCOUNT_NAME, result.discountName)
            assertEquals(-1.0, result.discountPercentage)
        }

    @Test
    fun `when a product has already had a discount applied then this discount should not be applied`() =
        runTest(testDispatcher) {
            val appliedDiscountModel = AppliedDiscountModel(
                productIds = listOf(bananaProductModel.id),
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