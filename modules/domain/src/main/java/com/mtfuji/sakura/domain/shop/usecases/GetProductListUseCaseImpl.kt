package com.mtfuji.sakura.domain.shop.usecases

import com.mtfuji.sakura.data.shop.ShoppingItemsRepository
import com.mtfuji.sakura.domain.shop.parser.toModel
import com.mtfuji.sakura.domainmodels.shop.ProductModel
import com.mtfuji.sakura.utilities.DispatcherProvider
import com.mtfuji.sakura.utilities.Outcome
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetProductListUseCaseImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: ShoppingItemsRepository
): GetProductListUseCase {
    override fun getProducts(): Flow<Outcome<List<ProductModel>>> = flow {
        try {
            emit(Outcome.Success(repository.getProducts().toModel()))
        } catch (ex: Exception) {
            emit(Outcome.Error(ex))
        }
    }.flowOn(dispatcherProvider.io)
}