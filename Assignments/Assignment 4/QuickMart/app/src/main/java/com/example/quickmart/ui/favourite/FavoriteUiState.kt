package com.example.quickmart.ui.favourite

import com.example.quickmart.repository.FavouriteRepository

data class FavoriteUiState(
    val favorites: List<Boolean> = FavouriteRepository.getFavouritesBoolean().toList()
) {
}