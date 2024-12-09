package com.mtfuji.sakura.shoppingcart.di

import com.mtfuji.sakura.shoppingcart.ui.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::HomeViewModel)
}