package com.socialite.solite_pos_mvp.ui.screens.cart

import com.socialite.domain.model.CartItem
import com.socialite.domain.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartManager @Inject constructor() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    fun addToCart(product: Product) {
        val currentItems = _cartItems.value.toMutableList()
        val existingItem = currentItems.find { it.product.id == product.id }

        if (existingItem != null) {
            val index = currentItems.indexOf(existingItem)
            currentItems[index] = existingItem.copy(quantity = existingItem.quantity + 1)
        } else {
            currentItems.add(CartItem(product = product, quantity = 1))
        }

        _cartItems.value = currentItems
    }

    fun updateQuantity(productId: Long, quantity: Int) {
        val currentItems = _cartItems.value.toMutableList()
        val item = currentItems.find { it.product.id == productId }

        if (item != null) {
            if (quantity <= 0) {
                currentItems.remove(item)
            } else {
                val index = currentItems.indexOf(item)
                currentItems[index] = item.copy(quantity = quantity)
            }
            _cartItems.value = currentItems
        }
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }

    fun getTotalPrice(): Double {
        return _cartItems.value.sumOf { it.totalPrice }
    }
}
