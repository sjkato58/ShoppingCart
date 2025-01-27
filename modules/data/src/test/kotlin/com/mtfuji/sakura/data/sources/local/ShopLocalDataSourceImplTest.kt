package com.mtfuji.sakura.data.sources.local

import com.mtfuji.sakura.data.shop.sources.local.ShopLocalDataSourceImpl
import com.mtfuji.sakura.dataModels.shop.ApiProductModel
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
class ShopLocalDataSourceImplTest {

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

    companion object {
        const val PRODUCT_LIST_KEY = "product_list"
        const val PRODUCT_ID = "12345"
        const val PRODUCT_NAME = "Apple"
        const val PRODUCT_PRICE = "0.75"
        const val PRODUCT_DESCRIPTION = "Pink Lady, Single, Fresh and firm"
        const val PRODUCT_IMAGE = "misc"
        const val PRODUCT_CATEGORY = "Fruit"
        const val PRODUCT_LIST_VALUE = "[{\"id\":\"$PRODUCT_ID\",\"name\":\"$PRODUCT_NAME\",\"price\":$PRODUCT_PRICE,\"description\":\"$PRODUCT_DESCRIPTION\",\"imageUrl\":\"$PRODUCT_IMAGE\",\"category\":\"$PRODUCT_CATEGORY\"}]"
    }

    private val exampleProduct = ApiProductModel(
        id = PRODUCT_ID,
        name = PRODUCT_NAME,
        price = PRODUCT_PRICE.toDouble(),
        description = PRODUCT_DESCRIPTION,
        imageUrl = PRODUCT_IMAGE,
        category = PRODUCT_CATEGORY
    )

    private lateinit var dataSource: ShopLocalDataSourceImpl

    @BeforeEach
    fun setUp() {
        dataSource = ShopLocalDataSourceImpl(dispatcherProvider)
    }

    @Test
    fun `when data is saved to the data source then the source will return that data upon requerst`() = runTest(testDispatcher) {
        val exampleList = listOf(exampleProduct)
        dataSource.setShoppingData(exampleList)
        advanceUntilIdle()

        val result = dataSource.getShoppingData()
        advanceUntilIdle()
        //println("result $result")

        assertTrue(result.isNotEmpty())
        assertEquals(exampleList, result)
    }

}