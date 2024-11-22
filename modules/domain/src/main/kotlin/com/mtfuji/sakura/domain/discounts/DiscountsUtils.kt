package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domainmodels.discounts.AppliedDiscountModel

fun List<AppliedDiscountModel>.obtainProductIds(): Set<String> {
    val mutableList = mutableSetOf<String>()
    this.forEach { appliedDiscountModel ->
        appliedDiscountModel.productIds.forEach {
            if (mutableList.contains(it).not()) {
                mutableList.add(it)
            }
        }
    }
    return mutableList
}