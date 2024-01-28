package com.example.quickmart.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickmart.model.Product
import com.example.quickmart.repository.CartRepository
import com.example.quickmart.repository.FavouriteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class FavouriteViewModel : ViewModel() {
    val favourites = FavouriteRepository.observeFavouritesList().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun onRemoveProductFromFavourite(product: Product) {
        FavouriteRepository.toggleFavourite(product)
    }

    fun addAllFavToCart() {
        CartRepository.addCartItems(FavouriteRepository.favouriteItems)
    }

}