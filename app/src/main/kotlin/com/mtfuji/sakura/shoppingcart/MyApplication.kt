package com.mtfuji.sakura.shoppingcart

import android.app.Application
import com.mtfuji.sakura.shoppingcart.di.DefaultKoinInitializer

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        DefaultKoinInitializer.boot(this)
    }
}