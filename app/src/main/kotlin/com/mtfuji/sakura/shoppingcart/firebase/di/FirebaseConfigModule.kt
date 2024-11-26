package com.mtfuji.sakura.shoppingcart.firebase.di

import com.mtfuji.sakura.shoppingcart.firebase.FirebaseConfigManager
import com.mtfuji.sakura.shoppingcart.firebase.FirebaseConfigManagerImpl
import com.mtfuji.sakura.shoppingcart.firebase.usecases.InitFirebaseUseCase
import com.mtfuji.sakura.shoppingcart.firebase.usecases.InitFirebaseUseCaseImpl
import com.mtfuji.sakura.shoppingcart.firebase.usecases.LoadShopProductListUseCase
import com.mtfuji.sakura.shoppingcart.firebase.usecases.LoadShopProductListUseCaseImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val fireBaseConfigModule = module {
    singleOf(::FirebaseConfigManagerImpl).bind<FirebaseConfigManager>()

    singleOf(::InitFirebaseUseCaseImpl).bind<InitFirebaseUseCase>()

    singleOf(::LoadShopProductListUseCaseImpl).bind<LoadShopProductListUseCase>()
}