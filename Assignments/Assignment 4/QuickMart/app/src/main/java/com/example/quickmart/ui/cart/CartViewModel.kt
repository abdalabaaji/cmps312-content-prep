package com.example.quickmart.ui.cart

import androidx.lifecycle.ViewModel
import com.example.quickmart.model.Product
import com.example.quickmart.repository.CartRepository
import com.example.quickmart.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CartViewModel : ViewModel() {
    private val _cartItems = MutableStateFlow(CartRepository.cartItems)
    val cartItems = _cartItems.asStateFlow()
    private val _total = MutableStateFlow(CartRepository.getCartTotal())
    val total = _total.asStateFlow()
    private val _productsInfo = MutableStateFlow(getProductsInfo())
    val productsInfo = _productsInfo.asStateFlow()

    fun updateCart(productId: String, quantity: Int) {
        _cartItems.value = CartRepository.updateItem(productId, quantity)
        _total.value = CartRepository.getCartTotal()
        _productsInfo.value = getProductsInfo()
    }

    fun clearCart() {
        _cartItems.value = CartRepository.clearItems()
        _total.value = CartRepository.getCartTotal()
        _productsInfo.value = getProductsInfo()
    }

    private fun getProductsInfo(): List<Pair<Product, Double>> {
        return _cartItems.value.map {
            Pair(
                ProductRepository.getProduct(it.productId),
                it.calculateTotalPrice()
            )
        }
    }
}