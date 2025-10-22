package com.socialite.domain.model

data class Order(
    val id: Long,
    val productId: Long,
    val productName: String,
    val quantity: Int,
    val price: Double,
    val totalPrice: Double,
    val timestamp: Long
)
