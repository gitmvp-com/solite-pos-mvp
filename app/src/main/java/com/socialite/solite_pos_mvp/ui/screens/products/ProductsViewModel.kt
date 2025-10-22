package com.socialite.solite_pos_mvp.ui.screens.products

import androidx.lifecycle.ViewModel
import com.socialite.domain.model.Product
import com.socialite.domain.repository.ProductRepository
import com.socialite.solite_pos_mvp.ui.screens.cart.CartManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val cartManager: CartManager
) : ViewModel() {

    val products: Flow<List<Product>> = productRepository.getAllProducts()

    fun addToCart(product: Product) {
        cartManager.addToCart(product)
    }
}
