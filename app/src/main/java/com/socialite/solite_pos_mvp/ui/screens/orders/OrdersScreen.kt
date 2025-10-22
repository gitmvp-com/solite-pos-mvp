package com.socialite.solite_pos_mvp.ui.screens.orders

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun OrdersScreen(
    viewModel: OrdersViewModel = hiltViewModel()
) {
    val orders by viewModel.orders.collectAsState(initial = emptyList())
    val totalSales by viewModel.totalSales.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            elevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Total Sales",
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "$${String.format("%.2f", totalSales)}",
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        if (orders.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No orders yet",
                    style = MaterialTheme.typography.h6
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(orders) { order ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = 4.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = order.productName,
                                    style = MaterialTheme.typography.h6,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "x${order.quantity}",
                                    style = MaterialTheme.typography.h6,
                                    color = MaterialTheme.colors.primary
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = formatDate(order.timestamp),
                                style = MaterialTheme.typography.caption
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "$${String.format("%.2f", order.totalPrice)}",
                                style = MaterialTheme.typography.h6,
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault())
    return sdf.format(Date(timestamp))
}
