package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domain.dummyData.seaSaltStrollerProductModel
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

    @BeforeEach
    fun setUp() {
        discount = SpecificProductDiscount(
            productId = seaSaltStrollerProductModel.id,
            discountPercentage = 40.0,
            rating = 1,
            name = "Specific Product Discount"
        )
    }

    @Test
    fun `when a specific product discount is valid then it should be applied`() =
        runTest(testDispatcher) {
            val result = discount.applyToProduct(seaSaltStrollerProductModel, 4)
            advanceUntilIdle()
            assertEquals(1.6, result)
        }
}