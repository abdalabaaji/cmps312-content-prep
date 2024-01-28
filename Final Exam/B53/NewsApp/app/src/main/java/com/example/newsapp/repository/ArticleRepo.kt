package com.example.newsapp.repository

import android.content.Context
import com.example.newsapp.model.Article
import com.example.newsapp.model.Category
import kotlinx.serialization.json.Json

object CategoryOptions {
    const val NONE = "None"
    const val POLITICS = "Politics"
    const val BUSINESS = "Business"
    const val TECHNOLOGY = "Technology"
}

object ArticleRepo {
    private var articles = mutableListOf<Article>()
    private var categories = mutableListOf<Category>()

    fun getArticles(context: Context): List<Article> {
//        read from the assets folder
        val jsonText = context
            .assets
            .open("articles.json")
            .bufferedReader().use { it.readText() }

        articles = Json { ignoreUnknownKeys = true }
            .decodeFromString(jsonText)

        return articles

    }

    fun getCategories(context: Context): List<Category> {
//        read from the assets folder
        val jsonText = context
            .assets
            .open("categories.json")
            .bufferedReader().use { it.readText() }

        categories = Json { ignoreUnknownKeys = true }
            .decodeFromString(jsonText)

        return categories

    }

    fun filterArticles(context: Context, categoryId: Int): List<Article> = getArticles(context)
        .filter { it.categoryId == categoryId }
}