package com.socialite.domain.repository

import com.socialite.data.database.dao.ProductDao
import com.socialite.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
) : ProductRepository {

    override fun getAllProducts(): Flow<List<Product>> {
        return productDao.getAllProducts().map { entities ->
            entities.map { entity ->
                Product(
                    id = entity.id,
                    name = entity.name,
                    price = entity.price,
                    description = entity.description
                )
            }
        }
    }

    override suspend fun getProductById(productId: Long): Product? {
        return productDao.getProductById(productId)?.let { entity ->
            Product(
                id = entity.id,
                name = entity.name,
                price = entity.price,
                description = entity.description
            )
        }
    }
}
