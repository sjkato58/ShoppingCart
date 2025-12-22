package com.mtfuji.sakura.data.shop.di

import com.mtfuji.sakura.data.shop.ShoppingItemsRepository
import com.mtfuji.sakura.data.shop.ShoppingItemsRepositoryImpl
import com.mtfuji.sakura.data.shop.sources.local.ShopLocalDataSource
import com.mtfuji.sakura.data.shop.sources.local.ShopLocalDataSourceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val shopDataModule = module {
    singleOf(::ShoppingItemsRepositoryImpl).bind<ShoppingItemsRepository>()

    singleOf(::ShopLocalDataSourceImpl).bind<ShopLocalDataSource>()
}