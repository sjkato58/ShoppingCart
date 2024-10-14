package com.mtfuji.sakura.domain.repositories

import com.mtfuji.sakura.domain.models.CartItemModel
import com.mtfuji.sakura.domain.models.ProductModel
import com.mtfuji.sakura.domain.utils.DispatcherProvider
import kotlinx.coroutines.withContext

class CartRepositoryImpl(
    private val dispatcherProvider: DispatcherProvider
): CartRepository {
    private val items: MutableList<CartItemModel> = mutableListOf()

    override suspend fun addProduct(productModel: ProductModel, quantity: Int) {
        withContext(dispatcherProvider.io) {
            val existingItem = items.find { it.product.id == productModel.id }
            if (existingItem != null) {
                existingItem.quantity += quantity
            } else {
                items.add(CartItemModel(productModel, quantity))
            }
        }
    }

    override suspend fun removeProduct(productModel: ProductModel) {
        withContext(dispatcherProvider.io) {
            items.removeIf { it.product.id == productModel.id }
        }
    }

    override suspend fun reduceProductQuantity(productModel: ProductModel, quantity: Int) {
        withContext(dispatcherProvider.io) {
            val existingItem = items.find { it.product.id == productModel.id }
            if (existingItem != null) {
                if (existingItem.quantity == 1 || existingItem.quantity < quantity) {
                    items.removeIf { it.product.id == productModel.id }
                } else {
                    existingItem.quantity -= quantity
                }
            }
        }
    }

    override suspend fun getTotalCost(): Double = withContext(dispatcherProvider.io) {
        items.sumOf { it.product.price * it.quantity }
    }

    override suspend fun getItems(): List<CartItemModel> = withContext(dispatcherProvider.io) {
        items
    }
}