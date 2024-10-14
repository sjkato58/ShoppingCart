package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domain.dummyData.appleProductModel
import com.mtfuji.sakura.domain.dummyData.baconLettuceTomatoProductModel
import com.mtfuji.sakura.domain.dummyData.bananaProductModel
import com.mtfuji.sakura.domain.dummyData.seaSaltStrollerProductModel
import com.mtfuji.sakura.domain.models.CartItemModel
import com.mtfuji.sakura.domain.utils.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
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

    @BeforeEach
    fun setUp() {
        discount = BuyTwoGetOneFreeDiscount(
            productId = appleProductModel.id,
            rating = 2,
            name = "Buy 2 get 1 free Discount"
        )
    }

    @Test
    fun `when a buy two get one free discount is valid then it should be applied`() =
        runTest(testDispatcher) {
            val result = discount.applyToProduct(
                product = appleProductModel,
                quantity = 7
            )
            advanceUntilIdle()
            assertEquals(1.5, result)
        }
}