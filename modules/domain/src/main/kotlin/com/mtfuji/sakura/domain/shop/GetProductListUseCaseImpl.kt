package com.mtfuji.sakura.domain.shop

import com.mtfuji.sakura.data.repositories.ShopRepository
import com.mtfuji.sakura.dataModels.ProductModel
import com.mtfuji.sakura.utilities.DispatcherProvider
import com.mtfuji.sakura.utilities.Outcome
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetProductListUseCaseImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: ShopRepository
): GetProductListUseCase {
    override fun getProducts(): Flow<Outcome<List<ProductModel>>> = flow {
        try {
            val productList = repository.getProducts()
            emit(Outcome.Success(productList))
        } catch (ex: Exception) {
            emit(Outcome.Error(ex))
        }
    }.flowOn(dispatcherProvider.io)
}