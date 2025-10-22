package com.socialite.domain.repository

import com.socialite.domain.model.Order
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun getAllOrders(): Flow<List<Order>>
    suspend fun createOrder(order: Order)
    suspend fun getTotalSales(): Double
}
