package com.socialite.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.socialite.data.database.dao.OrderDao
import com.socialite.data.database.dao.ProductDao
import com.socialite.data.database.entity.OrderEntity
import com.socialite.data.database.entity.ProductEntity

@Database(
    entities = [
        ProductEntity::class,
        OrderEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun orderDao(): OrderDao
}
