package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.data.repositories.CartRepositoryImpl
import com.mtfuji.sakura.datatest.appleProductModel
import com.mtfuji.sakura.datatest.bananaProductModel
import com.mtfuji.sakura.utilities.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCartTotalCostUseCaseImplTest {

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
    private lateinit var useCase: GetCartTotalCostUseCaseImpl

    @Before
    fun setUp() {
        repository = CartRepositoryImpl(dispatcherProvider)
        useCase = GetCartTotalCostUseCaseImpl(
            dispatcherProvider = dispatcherProvider,
            cartRepository = repository
        )
    }

    @Test
    fun `when requesting the total price in the shopping cart then all products are added together appropriately`() =
        runTest(testDispatcher) {
            repository.addProduct(appleProductModel, 3)
            repository.addProduct(bananaProductModel, 1)

            val totalPrice = useCase.execute()
            advanceUntilIdle()
            val expected = (appleProductModel.price * 3) + (bananaProductModel.price)
            assertEquals(expected, totalPrice)
        }
}