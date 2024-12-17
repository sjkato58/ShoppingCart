package com.mtfuji.sakura.domain.di

import com.mtfuji.sakura.domain.shop.GetProductListUseCase
import com.mtfuji.sakura.domain.shop.GetProductListUseCaseImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val shopModule = module {
    singleOf(::GetProductListUseCaseImpl).bind<GetProductListUseCase>()
}