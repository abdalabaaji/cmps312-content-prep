package com.example.quickmart.ui.product

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.quickmart.model.CartItem
import com.example.quickmart.model.Category
import com.example.quickmart.model.Product
import com.example.quickmart.repository.CartRepository
import com.example.quickmart.repository.FavouriteRepository
import com.example.quickmart.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductViewModel : ViewModel() {
    private val _products = MutableStateFlow(ProductRepository.productList)
    val products = _products.asStateFlow()
    private val _productsInfo = MutableStateFlow(getProductsInfo())
    val productsInfo = _productsInfo.asStateFlow()
    var query by mutableStateOf("")
        private set
    private var filter by mutableStateOf(Category("All" , 0))
    lateinit var categories: List<Category>
        private set

    lateinit var selectedProduct : Product

    fun updateList(query: String = this.query, filter: Category = this.filter) {
        this.query = query
        this.filter = filter
        _products.value = ProductRepository.getProducts(query, filter)
        _productsInfo.value = getProductsInfo()
    }

    fun addToCart(product: Product) {
        CartRepository.addCartItem(CartItem(product.id, 1))
        _productsInfo.value = getProductsInfo()
    }

    private fun getProductsInfo(): List<Pair<Int, Boolean>> {
        val quantities = CartRepository.getQuantities(_products.value)
        val favourites = FavouriteRepository.getFavouritesBoolean()
        return List(_products.value.size) { i -> Pair(quantities[i], favourites[i]) }
    }

    fun getCategories(context: Context): List<Category> {
        categories = ProductRepository.initCategories(context)
        return categories
    }

    fun getCategoryName(catId : Int) = categories.first { it.id == catId }.category

}