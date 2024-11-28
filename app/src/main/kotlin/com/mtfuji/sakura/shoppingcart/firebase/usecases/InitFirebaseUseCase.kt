package com.mtfuji.sakura.shoppingcart.firebase.usecases

interface InitFirebaseUseCase {
    suspend fun initializeFirebaseManager(defaults: Int): Boolean
}