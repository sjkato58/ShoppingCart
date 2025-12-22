package com.mtfuji.sakura.domain.usecases

import com.mtfuji.sakura.domain.discounts.Discounts

interface GetDiscountsUseCase {
    fun execute(): List<Discounts>
}