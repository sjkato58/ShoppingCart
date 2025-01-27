package com.mtfuji.sakura.data.di

import com.mtfuji.sakura.data.repositories.CartRepository
import com.mtfuji.sakura.data.repositories.CartRepositoryImpl
import com.mtfuji.sakura.data.shop.ShopRepository
import com.mtfuji.sakura.data.shop.ShopRepositoryImpl
import com.mtfuji.sakura.data.shop.sources.local.ShopLocalDataSource
import com.mtfuji.sakura.data.shop.sources.local.ShopLocalDataSourceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val cartDataModule = module {
    singleOf(::CartRepositoryImpl).bind<CartRepository>()
    singleOf(::ShopRepositoryImpl).bind<ShopRepository>()

    singleOf(::ShopLocalDataSourceImpl).bind<ShopLocalDataSource>()
}