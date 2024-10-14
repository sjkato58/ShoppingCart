package com.mtfuji.sakura.domain.di

import com.mtfuji.sakura.domain.utils.DefaultDispatcherProvider
import com.mtfuji.sakura.domain.utils.DispatcherProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coroutineModule = module {
    singleOf(::DefaultDispatcherProvider).bind<DispatcherProvider>()
}