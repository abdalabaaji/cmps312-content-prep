package com.example.quickmart.ui.product

import com.example.quickmart.model.Product
import com.example.quickmart.repository.ProductRepository

data class ProductUiState(
    val products: List<Product> = ProductRepository.getProducts(""),
    val query: String = "",
    val filter: String = "All",
) {
}