package com.mtfuji.sakura.shoppingcart.di

import android.app.Application
import com.mtfuji.sakura.data.di.cartDataModule
import com.mtfuji.sakura.domain.di.cartUseCaseModule
import com.mtfuji.sakura.domain.di.shopModule
import com.mtfuji.sakura.firebase.di.fireBaseConfigModule
import com.mtfuji.sakura.utilities.di.coroutineModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

object DefaultKoinInitializer: KoinInitializer {
    override fun boot(app: Application): KoinApplication {
        return startKoin {
            androidLogger()
            androidContext(app)
            modules(
                coroutineModule,
                cartDataModule,
                cartUseCaseModule,
                shopModule,
                discountsModule,
                fireBaseConfigModule,
                componentsModule,
                homeModule
            )
        }
    }
}