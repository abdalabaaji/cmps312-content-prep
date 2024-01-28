package com.example.quickmart.repository

import android.util.Log
import com.example.quickmart.model.Product

object FavouriteRepository {
    var favouriteItems = listOf<Product>()

    fun addToFavourites(product: Product) {
        favouriteItems = favouriteItems.plusElement(product)
    }
    fun removeFromFavourites(product: Product)  {
        favouriteItems = favouriteItems.filter { it.id != product.id }
        Log.d("TAGG", "removeFromFavourites: " + favouriteItems.size)
    }
    fun clearFavourites() {
        favouriteItems = emptyList()
    }

}