package com.example.newsapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.Articles.repo.ArticleRepo
import com.example.Articles.repo.CategoryOptions
import com.example.newsapp.model.Article


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier, updateArticle: (List<Article>) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    var showIcon by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Column(
        modifier = modifier
            .padding(2.dp)
            .background(Color.LightGray)
            .clip(RectangleShape),
    ) {
//        TextField(
//            value = searchQuery,
//            modifier = modifier.fillMaxWidth(),
//            onValueChange = {
//                searchQuery = it
//                showIcon = it.isNotEmpty()
//                updateArticle(ArticleRepo.searchArticles(context, it))
//            },
//
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Search,
//                    contentDescription = null
//                )
//            },
//            trailingIcon = {
//                if (showIcon)
//                    Icon(
//                        imageVector = Icons.Default.Close,
//                        contentDescription = null,
//                        modifier.clickable {
//                            searchQuery = ""
//                        })
//            },
//            colors = TextFieldDefaults.textFieldColors(
//                containerColor = MaterialTheme.colorScheme.primaryContainer
//            )
//
//        )
        CategoryDropDown(selectedOption = CategoryOptions.NONE) {
            if (it == CategoryOptions.NONE)
                updateArticle(ArticleRepo.getArticles(context))
            else
                updateArticle(ArticleRepo.filterArticles(context, it))
        }
    }
}