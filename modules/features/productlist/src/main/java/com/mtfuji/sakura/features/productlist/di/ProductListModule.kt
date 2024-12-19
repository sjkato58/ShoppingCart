package com.mtfuji.sakura.features.productlist.di

import com.mtfuji.sakura.features.productlist.ProductListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val productListModule = module {
    viewModelOf(::ProductListViewModel)
}