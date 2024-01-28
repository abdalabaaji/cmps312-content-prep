package com.example.newsapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Category (
    val id: Int,
    val category: String,
)