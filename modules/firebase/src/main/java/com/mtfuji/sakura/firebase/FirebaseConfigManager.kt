package com.mtfuji.sakura.firebase

interface FirebaseConfigManager {
    suspend fun setDefaults(defaults: Int): Unit
    suspend fun fetchAndActivate(): Boolean
    suspend fun getConfigValue(value: String): String
}