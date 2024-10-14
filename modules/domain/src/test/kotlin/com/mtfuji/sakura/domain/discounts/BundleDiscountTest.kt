package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domain.dummyData.baconLettuceTomatoProductModel
import com.mtfuji.sakura.domain.dummyData.bananaProductModel
import com.mtfuji.sakura.domain.dummyData.seaSaltStrollerProductModel
import com.mtfuji.sakura.domain.models.CartItemModel
import com.mtfuji.sakura.domain.models.ProductModel
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
class BundleDiscountTest {

    private val testDispatcher = StandardTestDispatcher()

    private val dispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = testDispatcher
        override val io: CoroutineDispatcher
            get() = testDispatcher
        override val default: CoroutineDispatcher
            get() = testDispatcher
        override val unconfined: CoroutineDispatcher
            get() = testDispatcher
    }

    private lateinit var discount: BundleDiscount

    @BeforeEach
    fun setUp() {
        discount = BundleDiscount(
            productIds = listOf(
                bananaProductModel.id,
                seaSaltStrollerProductModel.id,
                baconLettuceTomatoProductModel.id
            ),
            bundlePrice = 3.0,
            rating = 3,
            name = "Meal Bundle Discount"
        )
    }

    @Test
    fun `when a bundle discount is valid then it should be applied`() =
        runTest(testDispatcher) {
            val cartList = listOf(
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
            val result = discount.applyToCartList(cartList)
            println("result: $result")
            advanceUntilIdle()
            assertEquals(3.5, result)
        }
}