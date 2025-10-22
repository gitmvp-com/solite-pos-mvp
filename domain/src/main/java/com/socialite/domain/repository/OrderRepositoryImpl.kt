package com.socialite.domain.repository

import com.socialite.data.database.dao.OrderDao
import com.socialite.data.database.entity.OrderEntity
import com.socialite.domain.model.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val orderDao: OrderDao
) : OrderRepository {

    override fun getAllOrders(): Flow<List<Order>> {
        return orderDao.getAllOrders().map { entities ->
            entities.map { entity ->
                Order(
                    id = entity.id,
                    productId = entity.productId,
                    productName = entity.productName,
                    quantity = entity.quantity,
                    price = entity.price,
                    totalPrice = entity.totalPrice,
                    timestamp = entity.timestamp
                )
            }
        }
    }

    override suspend fun createOrder(order: Order) {
        val entity = OrderEntity(
            productId = order.productId,
            productName = order.productName,
            quantity = order.quantity,
            price = order.price,
            totalPrice = order.totalPrice,
            timestamp = order.timestamp
        )
        orderDao.insertOrder(entity)
    }

    override suspend fun getTotalSales(): Double {
        return orderDao.getTotalSales() ?: 0.0
    }
}
