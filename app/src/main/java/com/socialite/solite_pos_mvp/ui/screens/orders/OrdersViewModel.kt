package com.socialite.solite_pos_mvp.ui.screens.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.socialite.domain.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {

    val orders = orderRepository.getAllOrders()

    val totalSales: Flow<Double> = flow {
        emit(orderRepository.getTotalSales())
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)
}
