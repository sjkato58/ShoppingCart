package com.mtfuji.sakura.firebase.usecases

interface InitFirebaseUseCase {
    suspend fun initializeFirebaseManager(defaults: Int): Boolean
}