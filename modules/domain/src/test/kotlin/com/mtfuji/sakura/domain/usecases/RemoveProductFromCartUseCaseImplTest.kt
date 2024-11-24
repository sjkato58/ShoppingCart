package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.dataModels.CartItemModel
import com.mtfuji.sakura.data.repositories.CartRepositoryImpl
import com.mtfuji.sakura.datatest.appleProductModel
import com.mtfuji.sakura.datatest.bananaProductModel
import om.mtfuji.sakura.utilities.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RemoveProductFromCartUseCaseImplTest {

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
    private lateinit var useCase: RemoveProductFromCartUseCaseImpl

    @BeforeEach
    fun setUp() {
        repository = CartRepositoryImpl(dispatcherProvider)
        useCase = RemoveProductFromCartUseCaseImpl(
            dispatcherProvider = dispatcherProvider,
            cartRepository = repository
        )
    }

    @Test
    fun `when removing a product from the cart then the product is removed`() =
        runTest(testDispatcher) {
            repository.addProduct(appleProductModel, 3)
            repository.addProduct(bananaProductModel, 1)

            val cartList = repository.getItems()
            advanceUntilIdle()
            assertTrue(cartList.isNotEmpty())
            assertEquals(CartItemModel(appleProductModel, 3), cartList[0])
            assertEquals(CartItemModel(bananaProductModel, 1), cartList[1])

            useCase.execute(appleProductModel)
            useCase.execute(bananaProductModel)

            val cartList2 = repository.getItems()
            advanceUntilIdle()

            assertTrue(cartList2.isEmpty())
        }
}