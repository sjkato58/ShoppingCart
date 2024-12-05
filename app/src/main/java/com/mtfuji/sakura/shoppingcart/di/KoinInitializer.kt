package com.mtfuji.sakura.shoppingcart.di

import android.app.Application
import org.koin.core.KoinApplication

interface KoinInitializer {
    fun boot(app: Application): KoinApplication
}