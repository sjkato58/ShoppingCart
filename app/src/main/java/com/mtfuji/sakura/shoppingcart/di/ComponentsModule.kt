package com.mtfuji.sakura.shoppingcart.di

import com.mtfuji.sakura.data.shop.sources.remote.ShopRemoteDataSource
import com.mtfuji.sakura.shoppingcart.components.data.sources.remote.ShopRemoteDataSourceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val componentsModule = module {
    singleOf(::ShopRemoteDataSourceImpl).bind<ShopRemoteDataSource>()
}