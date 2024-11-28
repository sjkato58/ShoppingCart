package com.mtfuji.sakura.shoppingcart

import android.app.Application
import com.mtfuji.sakura.shoppingcart.di.DefaultKoinInitializer
import com.mtfuji.sakura.shoppingcart.firebase.usecases.InitFirebaseUseCase
import com.mtfuji.sakura.shoppingcart.firebase.usecases.LoadShopProductListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.getKoin

class MyApplication: Application() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()

        initKoin()
        initFirebaseRemoteConfig()
    }

    private fun initKoin() {
        DefaultKoinInitializer.boot(this)
    }

    private fun initFirebaseRemoteConfig() {
        scope.launch {
            val initFirebaseUseCase: InitFirebaseUseCase = getKoin().get()
            val isSuccess = initFirebaseUseCase.initializeFirebaseManager(R.xml.remote_config_defaults)

            if (isSuccess) {
                val loadShopProductListUseCase: LoadShopProductListUseCase = getKoin().get()
                loadShopProductListUseCase.loadProductList()
            }
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        scope.cancel()
    }
}