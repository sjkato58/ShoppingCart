package com.mtfuji.sakura.data.repositories

import com.mtfuji.sakura.dataModels.CartItemModel
import com.mtfuji.sakura.datatest.appleProductModel
import com.mtfuji.sakura.datatest.bananaProductModel
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
class CartRepositoryImplTest {

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

    @BeforeEach
    fun setUp() {
        repository = CartRepositoryImpl(dispatcherProvider)
    }

    @Test
    fun `when a new product is added to the cart then a new cart item is added with the appropriate information`() =
        runTest(testDispatcher) {
            repository.addProduct(appleProductModel, 1)
            repository.addProduct(bananaProductModel, 1)

            val cartList = repository.getItems()
            advanceUntilIdle()

            assertTrue(cartList.isNotEmpty())
            assertEquals(CartItemModel(appleProductModel, 1), cartList[0])
            assertEquals(CartItemModel(bananaProductModel, 1), cartList[1])
        }

    @Test
    fun `when adding to a product to the cart that already exists then the quantity should increase`() =
        runTest(testDispatcher) {
            repository.addProduct(appleProductModel, 1)
            repository.addProduct(bananaProductModel, 1)

            val cartList = repository.getItems()
            advanceUntilIdle()
            assertTrue(cartList.isNotEmpty())
            assertEquals(CartItemModel(appleProductModel, 1), cartList[0])
            assertEquals(CartItemModel(bananaProductModel, 1), cartList[1])

            repository.addProduct(appleProductModel, 2)

            val cartList2 = repository.getItems()
            advanceUntilIdle()

            assertTrue(cartList2.isNotEmpty())
            assertEquals(CartItemModel(appleProductModel, 3), cartList2[0])
            assertEquals(CartItemModel(bananaProductModel, 1), cartList2[1])
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

            repository.removeProduct(appleProductModel)
            repository.removeProduct(bananaProductModel)

            val cartList2 = repository.getItems()
            advanceUntilIdle()

            assertTrue(cartList2.isEmpty())
        }

    @Test
    fun `when reducing a products quantity then the product quantity should be reduced appropriately`() =
        runTest(testDispatcher) {
            repository.addProduct(appleProductModel, 3)
            repository.addProduct(bananaProductModel, 1)

            val cartList = repository.getItems()
            advanceUntilIdle()
            assertTrue(cartList.isNotEmpty())
            assertEquals(CartItemModel(appleProductModel, 3), cartList[0])
            assertEquals(CartItemModel(bananaProductModel, 1), cartList[1])

            repository.reduceProductQuantity(appleProductModel)
            repository.reduceProductQuantity(bananaProductModel)

            val cartList2 = repository.getItems()
            advanceUntilIdle()

            assertTrue(cartList2.isNotEmpty())
            assertEquals(CartItemModel(appleProductModel, 2), cartList2[0])
        }

    @Test
    fun `when requesting the total price in the shopping cart then all products are added together appropriately`() =
        runTest(testDispatcher) {
            repository.addProduct(appleProductModel, 3)
            repository.addProduct(bananaProductModel, 1)

            val cartList = repository.getItems()
            advanceUntilIdle()
            assertTrue(cartList.isNotEmpty())
            assertEquals(CartItemModel(appleProductModel, 3), cartList[0])
            assertEquals(CartItemModel(bananaProductModel, 1), cartList[1])
            val totalPrice = repository.getTotalCost()
            val expected = (appleProductModel.price * 3) + (bananaProductModel.price)
            assertEquals(expected, totalPrice)
        }
}