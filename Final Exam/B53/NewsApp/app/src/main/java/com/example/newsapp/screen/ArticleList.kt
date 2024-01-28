package com.example.newsapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.Articles.repo.ArticleRepo
import com.example.newsapp.model.Article
import com.example.newsapp.ui.theme.NewsAppTheme


@Composable
fun ArticleList(articles: List<Article>, contentPaddingValues: PaddingValues) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = contentPaddingValues
    ) {
        items(articles) {
            ArticleCard(article = it)
        }
    }
}


@Composable
fun ArticleCard(article: Article) {

//    get image using a name from resource folder
    val imageId = LocalContext.current.resources.getIdentifier(
        article.image,
        "drawable",
        LocalContext.current.packageName
    )

    Card(
        //shape = MaterialTheme.shapes.medium,
        shape = RoundedCornerShape(8.dp),
        // modifier = modifier.size(280.dp, 240.dp)
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
        //set card elevation of the card
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(imageId),
                contentDescription = null, // decorative
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.headlineLarge
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Text(text = "by : ${article.author}", fontWeight = FontWeight.ExtraBold)
                    Text(text = article.date, fontWeight = FontWeight.ExtraBold)

                }
                Text(text = article.article)

                Text(
                    text = article.category,
                    modifier = Modifier.align(Alignment.End),
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArticleListPreview() {
    val stadiums = ArticleRepo.getArticles(LocalContext.current)
    NewsAppTheme {
        ArticleList(articles = stadiums, PaddingValues(10.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleCardPreview() {
    val article = ArticleRepo.getArticles(LocalContext.current)[0]
    NewsAppTheme {
        ArticleCard(article = article)
    }
}
