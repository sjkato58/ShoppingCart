package com.mtfuji.sakura.data.di

import com.mtfuji.sakura.data.repositories.CartRepository
import com.mtfuji.sakura.data.repositories.CartRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val cartDataModule = module {
    singleOf(::CartRepositoryImpl).bind<CartRepository>()
}