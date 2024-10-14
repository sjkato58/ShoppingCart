package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.domain.dummyData.appleProductModel
import com.mtfuji.sakura.domain.dummyData.bananaProductModel
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

    @BeforeEach
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