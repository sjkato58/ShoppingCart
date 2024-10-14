package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.domain.dummyData.appleProductModel
import com.mtfuji.sakura.domain.dummyData.bananaProductModel
import com.mtfuji.sakura.domain.models.CartItemModel
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
class AddProductToCartUseCaseImplTest {

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
    private lateinit var usecase: AddProductToCartUseCaseImpl

    @BeforeEach
    fun setUp() {
        repository = CartRepositoryImpl(dispatcherProvider)
        usecase = AddProductToCartUseCaseImpl(
            dispatcherProvider = dispatcherProvider,
            cartRepository = repository
        )
    }

    @Test
    fun `when a new product is added to the cart then a new cart item is added with the appropriate information`() =
        runTest(testDispatcher) {
            usecase.execute(appleProductModel, 1)
            usecase.execute(bananaProductModel, 1)

            val cartList = repository.getItems()
            advanceUntilIdle()

            assertTrue(cartList.isNotEmpty())
            assertEquals(CartItemModel(appleProductModel, 1), cartList[0])
            assertEquals(CartItemModel(bananaProductModel, 1), cartList[1])
        }
}