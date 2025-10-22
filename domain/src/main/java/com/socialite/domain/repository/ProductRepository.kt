package com.socialite.domain.repository

import com.socialite.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAllProducts(): Flow<List<Product>>
    suspend fun getProductById(productId: Long): Product?
}
