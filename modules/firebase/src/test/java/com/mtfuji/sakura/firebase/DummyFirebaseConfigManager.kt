package com.mtfuji.sakura.firebase

import com.mtfuji.sakura.utilities.DispatcherProvider
import kotlinx.coroutines.withContext
import org.jetbrains.annotations.TestOnly


class DummyFirebaseConfigManager(
    private val dispatcherProvider: DispatcherProvider
): FirebaseConfigManager {
    private val defaults: MutableMap<String, Any> = mutableMapOf()
    private var wasSuccessful: Boolean = false
    private var exception: Exception? = null

    @TestOnly
    fun setTestDefault(defaults: Map<String, Any>) {
        this.defaults.putAll(defaults)
    }

    @TestOnly
    fun setWasSuccessful(wasSuccessful: Boolean) {
        this.wasSuccessful = wasSuccessful
    }

    @TestOnly
    fun setException(exception: Exception?) {
        this.exception = exception
    }

    override suspend fun setDefaults(defaults: Int) {}

    override suspend fun fetchAndActivate(): Boolean = withContext(dispatcherProvider.default) {
        exception?.let { throw it }
        wasSuccessful
    }

    override suspend fun getConfigValue(value: String): String = withContext(dispatcherProvider.default) {
        defaults[value].toString()
    }
}
