package com.mtfuji.sakura.firebase.di

import com.mtfuji.sakura.firebase.FirebaseConfigManager
import com.mtfuji.sakura.firebase.FirebaseConfigManagerImpl
import com.mtfuji.sakura.firebase.usecases.InitFirebaseUseCase
import com.mtfuji.sakura.firebase.usecases.InitFirebaseUseCaseImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val fireBaseConfigModule = module {
    singleOf(::FirebaseConfigManagerImpl).bind<FirebaseConfigManager>()

    singleOf(::InitFirebaseUseCaseImpl).bind<InitFirebaseUseCase>()
}