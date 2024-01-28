package com.example.quickmart.repository

import android.util.Log
import com.example.quickmart.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FavouriteRepository {
    var favouriteItems = listOf<Product>()

    fun toggleFavourite(product: Product){
        favouriteItems = if (favouriteItems.find { it.id == product.id } == null)
            favouriteItems.plusElement(product)
        else
            favouriteItems.minusElement(product)
        Log.d("TAG ME", favouriteItems.size.toString())

        observeFavouritesList()
    }

    fun observeFavouritesList(): Flow<List<Product>> = flow {
        emit(favouriteItems)
    }

    fun clearFavourites() {
        favouriteItems = listOf()
        observeFavouritesList()
    }
    fun getFavouritesBoolean(): List<Boolean> {
        return ProductRepository.productList.map { favouriteItems.contains(it) }
    }
}