package com.mtfuji.sakura.firebase.usecases

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
import java.lang.Exception

@OptIn(ExperimentalCoroutinesApi::class)
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
        const val PRODUCT_ID = "12345"
        const val PRODUCT_NAME = "Apple"
        const val PRODUCT_PRICE = "0.75"
        const val PRODUCT_DESCRIPTION = "Pink Lady, Single, Fresh and firm"
        const val PRODUCT_IMAGE = "misc"
        const val PRODUCT_CATEGORY = "Fruit"
        const val PRODUCT_LIST_VALUE = "[{\"id\":\"$PRODUCT_ID\",\"name\":\"$PRODUCT_NAME\",\"price\":$PRODUCT_PRICE,\"description\":\"$PRODUCT_DESCRIPTION\",\"imageUrl\":\"$PRODUCT_IMAGE\",\"category\":\"$PRODUCT_CATEGORY\"}]"
        const val EXCEPTION_MESSAGE = "Something went dreadfully wrong!"
    }

    private val dummyDefault: Map<String, Any> = mapOf(
        Pair(PRODUCT_LIST_KEY, PRODUCT_LIST_VALUE)
    )

    private lateinit var firebaseConfigManager: DummyFirebaseConfigManager
    private lateinit var useCase: InitFirebaseUseCaseImpl

    @BeforeEach
    fun setup() {
        firebaseConfigManager = DummyFirebaseConfigManager(dispatcherProvider)
        useCase = InitFirebaseUseCaseImpl(
            dispatcherProvider, firebaseConfigManager
        )
    }

    @Test
    fun `when we initialize the firebase config and it is successful then a positive response is returned`() =
        runTest(testDispatcher) {
            firebaseConfigManager.setTestDefault(dummyDefault)
            firebaseConfigManager.setWasSuccessful(true)

            val result = useCase.initializeFirebaseManager(0)
            advanceUntilIdle()

            assertEquals(true, result)
        }

    @Test
    fun `when we initialize the firebase config but there is no config update then a negative response is returned`() =
        runTest(testDispatcher) {
            firebaseConfigManager.setTestDefault(dummyDefault)
            firebaseConfigManager.setWasSuccessful(false)

            val result = useCase.initializeFirebaseManager(0)
            advanceUntilIdle()

            assertEquals(false, result)
        }

    @Test
    fun `when we initialize the firebase config but there is an error then that error is returned`() =
        runTest(testDispatcher) {
            val defaultException = Exception(EXCEPTION_MESSAGE)
            firebaseConfigManager.setTestDefault(dummyDefault)
            firebaseConfigManager.setWasSuccessful(false)
            firebaseConfigManager.setException(defaultException)

            var result: Exception? = null
            try {
                useCase.initializeFirebaseManager(0)
            } catch (e: Exception) {
                result = e
            }
            advanceUntilIdle()

            assertTrue(result != null)
            assertTrue(result is Exception)
            assertEquals(defaultException.message, result?.message)
        }
}