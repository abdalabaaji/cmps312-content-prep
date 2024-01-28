package com.example.quickmart.ui.details

data class ProductDetailsUiState(
    val currentProduct:String = "",
    val currentQuantity:Int = 1,
    val detailsExpand:Boolean = false
) {
}