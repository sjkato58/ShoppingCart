package com.mtfuji.sakura.shoppingcart.firebase.usecases

import com.mtfuji.sakura.shoppingcart.firebase.FirebaseConfigManager
import kotlinx.coroutines.withContext
import com.mtfuji.sakura.utilities.DispatcherProvider

class InitFirebaseUseCaseImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val firebaseConfigManager: FirebaseConfigManager
): InitFirebaseUseCase {
    override suspend fun initializeFirebaseManager(defaults: Int) {
        withContext(dispatcherProvider.default) {
            firebaseConfigManager.setDefaults(defaults)
            firebaseConfigManager.fetchAndActivate()
        }
    }
}