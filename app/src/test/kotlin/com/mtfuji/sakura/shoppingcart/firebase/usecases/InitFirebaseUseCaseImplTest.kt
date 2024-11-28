package com.mtfuji.sakura.shoppingcart.firebase.usecases

import com.mtfuji.sakura.shoppingcart.firebase.FirebaseConfigManager
import com.mtfuji.sakura.utilities.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class InitFirebaseUseCaseImplTest {

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
        const val PRODUCT_LIST_VALUE = "[{\"id\":\"12345\",\"name\":\"Apple\",\"price\":0.75,\"description\":\"Pink Lady, Single, Fresh and firm\",\"imageUrl\":\"misc\",\"category\":\"Fruit\"}]"
    }

    private val dummyDefault: Map<String, Any> = mapOf(
        Pair(PRODUCT_LIST_KEY, PRODUCT_LIST_VALUE)
    )

    //private lateinit var useCase: InitFirebaseUseCaseImpl

    @BeforeEach
    fun setUp() {
        /*useCase = InitFirebaseUseCaseImpl(
            dispatcherProvider,
            object : FirebaseConfigManager {
                override suspend fun setDefaults(defaults: Int) { }

                override suspend fun fetchAndActivate(): Boolean = true

                override suspend fun getConfigValue(value: String): String = ""
            }
        )*/
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
        try {
            val clazz = Class.forName("com.mtfuji.sakura.shoppingcart.firebase.usecases.InitFirebaseUseCase")
            println("Class found: ${clazz.name}")
        } catch (e: ClassNotFoundException) {
            println("Class not found: ${e.message}")
            e.printStackTrace()
        }
    }
}
