package com.mtfuji.sakura.home.di

import com.mtfuji.sakura.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::HomeViewModel)
}