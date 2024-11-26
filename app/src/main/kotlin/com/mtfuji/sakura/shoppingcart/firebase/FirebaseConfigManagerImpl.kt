package com.mtfuji.sakura.shoppingcart.firebase

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import com.mtfuji.sakura.utilities.DispatcherProvider

class FirebaseConfigManagerImpl(
    private val dispatcherProvider: DispatcherProvider
): FirebaseConfigManager {

    private val remoteConfig = FirebaseRemoteConfig.getInstance()

    override suspend fun setDefaults(defaults: Int): Unit = withContext(dispatcherProvider.default) {
        remoteConfig.setDefaultsAsync(defaults).await()
    }

    override suspend fun fetchAndActivate(): Boolean = withContext(dispatcherProvider.default) {
            try {
                remoteConfig.fetchAndActivate().await()
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

    override suspend fun getConfigValue(value: String): String = withContext(dispatcherProvider.default) {
        remoteConfig.getString(value)
    }
}