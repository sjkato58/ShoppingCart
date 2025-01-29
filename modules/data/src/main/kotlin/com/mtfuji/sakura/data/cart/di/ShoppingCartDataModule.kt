package com.mtfuji.sakura.data.cart.di

import com.mtfuji.sakura.data.cart.ShoppingCartRepository
import com.mtfuji.sakura.data.cart.ShoppingCartRepositoryImpl
import com.mtfuji.sakura.data.cart.sources.local.ShoppingCartLocalDataSource
import com.mtfuji.sakura.data.cart.sources.local.ShoppingCartLocalDataSourceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val shoppingCartDataModule = module {
    singleOf(::ShoppingCartRepositoryImpl).bind<ShoppingCartRepository>()

    singleOf(::ShoppingCartLocalDataSourceImpl).bind<ShoppingCartLocalDataSource>()
}