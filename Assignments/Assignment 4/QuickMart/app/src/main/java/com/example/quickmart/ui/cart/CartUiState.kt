package com.example.quickmart.ui.cart

import com.example.quickmart.model.CartItem
import com.example.quickmart.repository.CartRepository

data class CartUiState(
    val cartItems: List<CartItem> = (CartRepository.cartItems),
    val total: Double = CartRepository.getCartTotal()
) {
}