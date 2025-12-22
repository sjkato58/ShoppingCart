package com.mtfuji.sakura.domain.di

import com.mtfuji.sakura.domain.usecases.AddProductToCartUseCase
import com.mtfuji.sakura.domain.usecases.AddProductToCartUseCaseImpl
import com.mtfuji.sakura.domain.usecases.GetCartItemsUseCase
import com.mtfuji.sakura.domain.usecases.GetCartItemsUseCaseImpl
import com.mtfuji.sakura.domain.usecases.GetCartTotalCostUseCase
import com.mtfuji.sakura.domain.usecases.GetCartTotalCostUseCaseImpl
import com.mtfuji.sakura.domain.usecases.RemoveProductFromCartUseCase
import com.mtfuji.sakura.domain.usecases.RemoveProductFromCartUseCaseImpl
import com.mtfuji.sakura.domain.usecases.ReduceProductQuantityFromCartUseCase
import com.mtfuji.sakura.domain.usecases.ReduceProductQuantityFromCartUseCaseImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val cartUseCaseModule = module {
    singleOf(::AddProductToCartUseCaseImpl).bind<AddProductToCartUseCase>()
    singleOf(::GetCartItemsUseCaseImpl).bind<GetCartItemsUseCase>()
    singleOf(::GetCartTotalCostUseCaseImpl).bind<GetCartTotalCostUseCase>()
    singleOf(::RemoveProductFromCartUseCaseImpl).bind<RemoveProductFromCartUseCase>()
    singleOf(::ReduceProductQuantityFromCartUseCaseImpl).bind<ReduceProductQuantityFromCartUseCase>()
}