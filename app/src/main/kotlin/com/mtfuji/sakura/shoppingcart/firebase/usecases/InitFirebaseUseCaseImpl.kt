package com.mtfuji.sakura.shoppingcart.firebase.usecases

import com.mtfuji.sakura.shoppingcart.firebase.FirebaseConfigManager
import com.mtfuji.sakura.utilities.DispatcherProvider
import kotlinx.coroutines.withContext

class InitFirebaseUseCaseImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val firebaseConfigManager: FirebaseConfigManager
): InitFirebaseUseCase {
    override suspend fun initializeFirebaseManager(defaults: Int): Boolean =
        withContext(dispatcherProvider.default) {
            firebaseConfigManager.setDefaults(defaults)
            firebaseConfigManager.fetchAndActivate()
        }
}