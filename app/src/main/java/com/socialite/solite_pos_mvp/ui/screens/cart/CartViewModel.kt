package com.socialite.solite_pos_mvp.ui.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.socialite.domain.model.Order
import com.socialite.domain.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartManager: CartManager,
    private val orderRepository: OrderRepository
) : ViewModel() {

    val cartItems = cartManager.cartItems

    val totalPrice = cartItems.map { items ->
        items.sumOf { it.totalPrice }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    fun updateQuantity(productId: Long, quantity: Int) {
        cartManager.updateQuantity(productId, quantity)
    }

    fun checkout() {
        viewModelScope.launch {
            val items = cartItems.value
            val timestamp = System.currentTimeMillis()

            items.forEach { cartItem ->
                val order = Order(
                    id = 0,
                    productId = cartItem.product.id,
                    productName = cartItem.product.name,
                    quantity = cartItem.quantity,
                    price = cartItem.product.price,
                    totalPrice = cartItem.totalPrice,
                    timestamp = timestamp
                )
                orderRepository.createOrder(order)
            }

            cartManager.clearCart()
        }
    }
}
