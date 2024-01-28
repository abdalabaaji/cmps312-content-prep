package com.example.newsapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    var title: String,
    var article: String,
    var image: String,
    var categoryId: Int,
    var author: String,
    var date: String
)


