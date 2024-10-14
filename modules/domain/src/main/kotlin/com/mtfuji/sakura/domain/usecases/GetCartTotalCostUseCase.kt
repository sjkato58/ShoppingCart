package com.mtfuji.sakura.domain.usecases

interface GetCartTotalCostUseCase {
    suspend fun execute(): Double
}