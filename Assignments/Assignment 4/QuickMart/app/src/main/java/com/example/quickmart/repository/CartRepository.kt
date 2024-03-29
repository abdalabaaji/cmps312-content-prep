package com.example.quickmart.repository

import com.example.quickmart.model.CartItem
import com.example.quickmart.model.Product

object CartRepository {
    var cartItems: List<CartItem> = listOf()

    fun clearItems(): List<CartItem> {
        cartItems = listOf()
        return cartItems
    }
    fun addCartItems(products: List<Product>) = products.map { CartItem(it.id, 1) }
            .forEach { addCartItem(it) }

    fun addCartItem(cartItem: CartItem) {
        if (cartItems.find { it.productId == cartItem.productId } == null) {
            var cart = cartItems.toMutableList()
            cart.add(cartItem)
            cartItems = cart.toList()
        } else
            updateItem(cartItem.productId, cartItem.quantity)
    }

    fun updateItem(productId: String, quantity: Int): List<CartItem> {
        var cart = cartItems.toMutableList()
        var item = cart.find { it.productId == productId }!!
        cart.elementAt(cart.indexOf(item)).quantity += quantity
        if (item.quantity == 0)
            cart.remove(item)
        cartItems = cart.toList()
        return cartItems
    }

    fun getCartTotal(): Double {
        return cartItems.fold(0.0) { acc, it -> acc + it.calculateTotalPrice() }
    }

    fun getQuantities(products: List<Product>): List<Int> {
        return products.map { cartItems.find { item -> item.productId == it.id }?.quantity ?: 0 }
    }
}