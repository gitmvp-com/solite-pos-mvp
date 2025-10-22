package com.socialite.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val productId: Long,
    val productName: String,
    val quantity: Int,
    val price: Double,
    val totalPrice: Double,
    val timestamp: Long
)
