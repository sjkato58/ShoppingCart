package com.mtfuji.sakura.firebase

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import kotlinx.coroutines.withContext
import com.mtfuji.sakura.utilities.DispatcherProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.single

class FirebaseConfigManagerImpl(
    private val dispatcherProvider: DispatcherProvider
): FirebaseConfigManager {

    private val remoteConfig: FirebaseRemoteConfig by lazy {
        FirebaseRemoteConfig.getInstance()
    }

    override suspend fun setDefaults(defaults: Int): Unit = withContext(dispatcherProvider.io) {
        return@withContext setDefaultsFlow(defaults)
            .catch { e ->
                Log.e("Firebase", "Error setting defaults", e)
                throw e
            }
            .collect()
    }

    private fun setDefaultsFlow(defaults: Int): Flow<Unit> = callbackFlow {
        try {
            val settings = FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(3600)
                .build()
            remoteConfig.setConfigSettingsAsync(settings)
                .addOnCompleteListener {  configTask ->
                    if (configTask.isSuccessful) {
                        remoteConfig.setDefaultsAsync(defaults)
                            .addOnCompleteListener {  defaultsTask ->
                                if (defaultsTask.isSuccessful) {
                                    trySend(Unit).isSuccess
                                    close()
                                } else {
                                    close(defaultsTask.exception)
                                }
                            }
                    } else {
                        close(configTask.exception)
                    }
                }
        } catch (ex: Exception) {
            close(ex)
        }
        awaitClose()
    }

    override suspend fun fetchAndActivate(): Boolean = withContext(dispatcherProvider.io) {
            return@withContext fetchAndActivateFlow()
                .catch { e ->
                    Log.e("Firebase", "Error in fetchAndActivate", e)
                    throw e
                }
                .single()
        }

    private fun fetchAndActivateFlow(): Flow<Boolean> = callbackFlow {
        try {
            remoteConfig.fetchAndActivate()
                .addOnSuccessListener { result ->
                    Log.w("Firebase", "fetchAndActivate-Config updated? $result")
                    trySend(true)
                    close()
                }
                .addOnFailureListener { exception ->
                    close(exception)
                }

        } catch (e: Exception) {
            e.printStackTrace()
            close(e)
        }
        awaitClose()
    }

    override suspend fun getConfigValue(value: String): String = withContext(dispatcherProvider.io) {
        remoteConfig.getString(value)
    }
}