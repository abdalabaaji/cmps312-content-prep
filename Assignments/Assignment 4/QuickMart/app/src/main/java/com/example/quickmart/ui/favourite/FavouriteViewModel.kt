package com.example.quickmart.ui.favourite

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.quickmart.model.Product
import com.example.quickmart.repository.CartRepository
import com.example.quickmart.repository.FavouriteRepository

class FavouriteViewModel : ViewModel() {
    var favourites = FavouriteRepository.favouriteItems.toMutableStateList()

    fun onRemoveProductFromFavourite(product: Product) {
        favourites.remove(product)
    }

    fun addAllFavToCart() {
        CartRepository.addCartItems(FavouriteRepository.favouriteItems)
        favourites.clear()
    }

    fun addToFavourite(product: Product) {
        favourites.add(product)
    }

    fun isInFavourite(product: Product): Boolean {
        return favourites.contains(product)
    }
}