package com.mtfuji.sakura.domain.shop.usecases

import com.mtfuji.sakura.domainmodels.shop.ProductModel
import com.mtfuji.sakura.utilities.Outcome
import kotlinx.coroutines.flow.Flow

interface GetProductListUseCase {
    fun getProducts(): Flow<Outcome<List<ProductModel>>>
}