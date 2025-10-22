package com.socialite.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.socialite.data.database.AppDatabase
import com.socialite.data.database.entity.ProductEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "solite_pos_mvp.db"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    // Pre-populate database with sample products
                    CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
                        val database = Room.databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            "solite_pos_mvp.db"
                        ).build()

                        val sampleProducts = listOf(
                            ProductEntity(name = "Coffee", price = 3.50, description = "Hot brewed coffee"),
                            ProductEntity(name = "Tea", price = 2.50, description = "Fresh tea"),
                            ProductEntity(name = "Sandwich", price = 5.00, description = "Chicken sandwich"),
                            ProductEntity(name = "Cake", price = 4.00, description = "Chocolate cake"),
                            ProductEntity(name = "Juice", price = 3.00, description = "Fresh orange juice"),
                            ProductEntity(name = "Salad", price = 4.50, description = "Caesar salad")
                        )
                        database.productDao().insertProducts(sampleProducts)
                    }
                }
            })
            .build()
    }

    @Provides
    fun provideProductDao(database: AppDatabase) = database.productDao()

    @Provides
    fun provideOrderDao(database: AppDatabase) = database.orderDao()
}
