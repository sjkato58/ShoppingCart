package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.domain.discounts.DiscountManagerImpl
import com.mtfuji.sakura.domain.dummyData.bundleDiscount
import com.mtfuji.sakura.domain.dummyData.buyTwoGetOneFreeDiscount
import com.mtfuji.sakura.domain.dummyData.percentageOffCartDiscount
import com.mtfuji.sakura.domain.dummyData.specificProductDiscount
import com.mtfuji.sakura.data.repositories.CartRepositoryImpl
import com.mtfuji.sakura.datatest.appleProductModel
import com.mtfuji.sakura.datatest.baconLettuceTomatoProductModel
import com.mtfuji.sakura.datatest.bananaProductModel
import com.mtfuji.sakura.datatest.seaSaltStrollerProductModel
import com.mtfuji.sakura.domainmodels.discounts.AppliedDiscountModel
import com.mtfuji.sakura.utilities.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CheckoutUseCaseImplTest {

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

    private lateinit var repository: CartRepositoryImpl
    private lateinit var discountManager: DiscountManagerImpl
    private lateinit var useCase: CheckoutUseCase

    @BeforeEach
    fun setUp() {
        repository = CartRepositoryImpl(dispatcherProvider)
        discountManager = DiscountManagerImpl(
            dispatcherProvider = dispatcherProvider,
            discounts = listOf(
                buyTwoGetOneFreeDiscount,
                bundleDiscount,
                specificProductDiscount,
                percentageOffCartDiscount
            ),
            cartRepository = repository
        )
        useCase = CheckoutUseCaseImpl(
            dispatcherProvider = dispatcherProvider,
            discountManager = discountManager
        )
    }

    @Test
    fun `when applying multiple discounts and one is to be ignored then the cart should apply those that apply appropriately`() = runTest(dispatcherProvider.io) {
        repository.addProduct(appleProductModel, 5)
        repository.addProduct(seaSaltStrollerProductModel, 2)
        repository.addProduct(bananaProductModel, 3)
        repository.addProduct(baconLettuceTomatoProductModel, 1)
        val result = discountManager.calculateTotalDiscount()
        advanceUntilIdle()
        println("result: $result")
        val expected = AppliedDiscountModel(
            listOf(
                bananaProductModel.id,
                seaSaltStrollerProductModel.id,
                baconLettuceTomatoProductModel.id
            ),
            bundleDiscount.name,
            1.75,
            -1.0
        )
        val expected2 = AppliedDiscountModel(
            listOf(appleProductModel.id),
            buyTwoGetOneFreeDiscount.name,
            0.75,
            -1.0
        )
        assertEquals(9.5, result.finalTotal)
        assertEquals(2.5, result.totalDiscount)
        assertTrue(result.appliedDiscounts.isNotEmpty())
        assertEquals(expected, result.appliedDiscounts[0])
        assertEquals(expected2, result.appliedDiscounts[1])
    }
}