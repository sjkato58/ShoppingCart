package com.mtfuji.sakura.domain.shop

import com.mtfuji.sakura.dataModels.ProductModel
import com.mtfuji.sakura.utilities.Outcome
import kotlinx.coroutines.flow.Flow

interface GetProductListUseCase {
    fun getProducts(): Flow<Outcome<List<ProductModel>>>
}