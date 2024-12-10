package com.mtfuji.sakura.firebase.usecases

import com.mtfuji.sakura.data.repositories.ShopRepositoryImpl
import com.mtfuji.sakura.dataModels.ProductModel
import com.mtfuji.sakura.firebase.DummyFirebaseConfigManager
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
class LoadShopProductListUseCaseImplTest {
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

    private val dummyDefault: Map<String, Any> = mapOf(
        Pair(PRODUCT_LIST_KEY, PRODUCT_LIST_VALUE)
    )

    private val exampleProduct = ProductModel(
        id = PRODUCT_ID,
        name = PRODUCT_NAME,
        price = PRODUCT_PRICE.toDouble(),
        description = PRODUCT_DESCRIPTION,
        imageUrl = PRODUCT_IMAGE,
        category = PRODUCT_CATEGORY
    )

    private lateinit var firebaseConfigManager: DummyFirebaseConfigManager
    private lateinit var repository: ShopRepositoryImpl
    private lateinit var useCase: LoadShopProductListUseCaseImpl

    @BeforeEach
    fun setup() {
        firebaseConfigManager = DummyFirebaseConfigManager(dispatcherProvider)
        repository = ShopRepositoryImpl(dispatcherProvider)
        useCase = LoadShopProductListUseCaseImpl(
            dispatcherProvider,
            firebaseConfigManager,
            repository
        )
    }

    @Test
    fun `when we the firebase config and it is successful then a positive response is returned`() =
        runTest(testDispatcher) {
            firebaseConfigManager.setTestDefault(dummyDefault)
            firebaseConfigManager.setWasSuccessful(true)

            useCase.loadProductList()
            advanceUntilIdle()
            val result = repository.getProducts()
            advanceUntilIdle()
            //println("result: $result")

            assertTrue(result.isNotEmpty())
            val expected = listOf(exampleProduct)
            assertEquals(expected, result)
        }
}