package com.mtfuji.sakura.shoppingcart

import android.app.Application
import com.google.firebase.FirebaseApp
import com.mtfuji.sakura.shoppingcart.di.DefaultKoinInitializer
import com.mtfuji.sakura.firebase.usecases.InitFirebaseUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.getKoin

class MyApplication: Application() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()

        initKoin()
        initFirebaseRemoteConfig()
    }

    private fun initKoin() {
        DefaultKoinInitializer.boot(this)
    }

    private fun initFirebaseRemoteConfig() {
        FirebaseApp.initializeApp(this@MyApplication)
        scope.launch {
            val initFirebaseUseCase: InitFirebaseUseCase = getKoin().get()
            initFirebaseUseCase.initializeFirebaseManager(R.xml.remote_config_defaults)
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        scope.cancel()
    }
}