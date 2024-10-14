package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domain.dummyData.appleProductModel
import com.mtfuji.sakura.domain.dummyData.baconLettuceTomatoProductModel
import com.mtfuji.sakura.domain.dummyData.bananaProductModel
import com.mtfuji.sakura.domain.dummyData.bundleDiscount
import com.mtfuji.sakura.domain.dummyData.buyTwoGetOneFreeDiscount
import com.mtfuji.sakura.domain.dummyData.carrotProductModel
import com.mtfuji.sakura.domain.dummyData.percentageOffCartDiscount
import com.mtfuji.sakura.domain.dummyData.seaSaltStrollerProductModel
import com.mtfuji.sakura.domain.dummyData.specificProductDiscount
import com.mtfuji.sakura.domain.models.AppliedDiscountModel
import com.mtfuji.sakura.domain.models.ProductModel
import com.mtfuji.sakura.domain.repositories.CartRepositoryImpl
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
class DiscountManagerImplTest {

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

    @BeforeEach
    fun setUp() {
        repository = CartRepositoryImpl(dispatcherProvider)
    }

    @Test
    fun `when applying a cart total discount then the cart should apply that discount`() = runTest(dispatcherProvider.io) {
        populateCartRepository(
            listOf(
                Pair(appleProductModel, 1),
                Pair(bananaProductModel, 1),
                Pair(carrotProductModel, 4)
            )
        )
        initDiscountManager(
            listOf(percentageOffCartDiscount)
        )
        val result = discountManager.calculateTotalDiscount()
        advanceUntilIdle()
        val expected = AppliedDiscountModel(
            "Cart",
            percentageOffCartDiscount.name,
            0.75
        )
        assertEquals(2.25, result.finalTotal)
        assertEquals(0.75, result.totalDiscount)
        assertTrue(result.appliedDiscounts.isNotEmpty())
        assertEquals(expected, result.appliedDiscounts[0])
    }

    @Test
    fun `when applying a buy 2 get 1 free discount then the cart should apply that discount`() = runTest(dispatcherProvider.io) {
        populateCartRepository(
            listOf(
                Pair(appleProductModel, 5),
                Pair(bananaProductModel, 1)
            )
        )
        initDiscountManager(
            listOf(buyTwoGetOneFreeDiscount)
        )
        val result = discountManager.calculateTotalDiscount()
        advanceUntilIdle()
        val expected = AppliedDiscountModel(
            appleProductModel.id,
            buyTwoGetOneFreeDiscount.name,
            0.75
        )
        assertEquals(4.25, result.finalTotal)
        assertEquals(0.75, result.totalDiscount)
        assertTrue(result.appliedDiscounts.isNotEmpty())
        assertEquals(expected, result.appliedDiscounts[0])
    }

    @Test
    fun `when applying a specific product discount then the cart should apply that discount`() = runTest(dispatcherProvider.io) {
        populateCartRepository(
            listOf(
                Pair(seaSaltStrollerProductModel, 4),
                Pair(appleProductModel, 2)
            )
        )
        initDiscountManager(
            listOf(specificProductDiscount)
        )
        val result = discountManager.calculateTotalDiscount()
        advanceUntilIdle()
        val expected = AppliedDiscountModel(
            seaSaltStrollerProductModel.id,
            specificProductDiscount.name,
            1.6
        )
        assertEquals(3.9, result.finalTotal)
        assertEquals(1.6, result.totalDiscount)
        assertTrue(result.appliedDiscounts.isNotEmpty())
        assertEquals(expected, result.appliedDiscounts[0])
    }

    @Test
    fun `when applying a bundle discount then the cart should apply that discount`() = runTest(dispatcherProvider.io) {
        populateCartRepository(
            listOf(
                Pair(seaSaltStrollerProductModel, 3),
                Pair(bananaProductModel, 3),
                Pair(baconLettuceTomatoProductModel, 2)
            )
        )
        initDiscountManager(
            listOf(bundleDiscount)
        )
        val result = discountManager.calculateTotalDiscount()
        advanceUntilIdle()
        val expected = AppliedDiscountModel(
            "Bundle[${bananaProductModel.id}, ${seaSaltStrollerProductModel.id}, ${baconLettuceTomatoProductModel.id}]",
            bundleDiscount.name,
            3.5
        )
        assertEquals(8.25, result.finalTotal)
        assertEquals(3.5, result.totalDiscount)
        assertTrue(result.appliedDiscounts.isNotEmpty())
        assertEquals(expected, result.appliedDiscounts[0])
    }

    @Test
    fun `when applying multiple discounts then the cart should apply those that apply appropriately`() = runTest(dispatcherProvider.io) {
        populateCartRepository(
            listOf(
                Pair(appleProductModel, 5),
                Pair(seaSaltStrollerProductModel, 1),
                Pair(bananaProductModel, 3),
                Pair(baconLettuceTomatoProductModel, 1)
            )
        )
        initDiscountManager(
            listOf(buyTwoGetOneFreeDiscount, bundleDiscount)
        )
        val result = discountManager.calculateTotalDiscount()
        advanceUntilIdle()
        val expected = AppliedDiscountModel(
            "Bundle[${bananaProductModel.id}, ${seaSaltStrollerProductModel.id}, ${baconLettuceTomatoProductModel.id}]",
            bundleDiscount.name,
            1.75
        )
        val expected2 = AppliedDiscountModel(
            appleProductModel.id,
            buyTwoGetOneFreeDiscount.name,
            0.75
        )
        assertEquals(8.5, result.finalTotal)
        assertEquals(2.5, result.totalDiscount)
        assertTrue(result.appliedDiscounts.isNotEmpty())
        assertEquals(expected, result.appliedDiscounts[0])
        assertEquals(expected2, result.appliedDiscounts[1])
    }

    @Test
    fun `when applying multiple discounts and one is to be ignored then the cart should apply those that apply appropriately`() = runTest(dispatcherProvider.io) {
        populateCartRepository(
            listOf(
                Pair(appleProductModel, 5),
                Pair(seaSaltStrollerProductModel, 2),
                Pair(bananaProductModel, 3),
                Pair(baconLettuceTomatoProductModel, 1)
            )
        )
        initDiscountManager(
            listOf(
                buyTwoGetOneFreeDiscount,
                bundleDiscount,
                specificProductDiscount,
                percentageOffCartDiscount
            )
        )
        val result = discountManager.calculateTotalDiscount()
        advanceUntilIdle()
        val expected = AppliedDiscountModel(
            "Bundle[${bananaProductModel.id}, ${seaSaltStrollerProductModel.id}, ${baconLettuceTomatoProductModel.id}]",
            bundleDiscount.name,
            1.75
        )
        val expected2 = AppliedDiscountModel(
            appleProductModel.id,
            buyTwoGetOneFreeDiscount.name,
            0.75
        )
        assertEquals(9.5, result.finalTotal)
        assertEquals(2.5, result.totalDiscount)
        assertTrue(result.appliedDiscounts.isNotEmpty())
        assertEquals(expected, result.appliedDiscounts[0])
        assertEquals(expected2, result.appliedDiscounts[1])
    }

    private suspend fun populateCartRepository(
        productsToAdd: List<Pair<ProductModel, Int>>
    ) {
        productsToAdd.forEach { pair ->
            repository.addProduct(pair.first, pair.second)
        }

    }

    private fun initDiscountManager(
        discountList: List<Discounts>
    ) {
        discountManager = DiscountManagerImpl(
            dispatcherProvider = dispatcherProvider,
            discounts = discountList,
            cartRepository = repository
        )
    }
}