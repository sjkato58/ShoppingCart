package com.mtfuji.sakura.data.di

import com.mtfuji.sakura.data.repositories.CartRepository
import com.mtfuji.sakura.data.repositories.CartRepositoryImpl
import com.mtfuji.sakura.data.repositories.ShopRepository
import com.mtfuji.sakura.data.repositories.ShopRepositoryImpl
import com.mtfuji.sakura.data.sources.local.ShopLocalDataSource
import com.mtfuji.sakura.data.sources.local.ShopLocalDataSourceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val cartDataModule = module {
    singleOf(::CartRepositoryImpl).bind<CartRepository>()
    singleOf(::ShopRepositoryImpl).bind<ShopRepository>()

    singleOf(::ShopLocalDataSourceImpl).bind<ShopLocalDataSource>()
}