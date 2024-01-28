package com.example.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.Articles.repo.ArticleRepo
import com.example.newsapp.screen.AddNewsInfoForm
import com.example.newsapp.screen.ArticleList
import com.example.newsapp.screen.TopBar
import com.example.newsapp.ui.theme.NewsAppTheme


//Today Code by Abd
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val context = LocalContext.current
    var articles by remember {
        mutableStateOf(ArticleRepo.getArticles(context))
    }
    var showAddScreen by remember {
        mutableStateOf(false)
    }
    val TAG = "MyApp"
    Log.d(TAG, "MyApp: $articles")
    Scaffold(
        topBar = {
            if (!showAddScreen)
                TopBar {
                    articles = it
                }
        },
        floatingActionButton = {
            if (!showAddScreen)
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        Modifier.clickable { showAddScreen = true })
                }
        }
    ) {
        if (!showAddScreen)
            ArticleList(articles = articles, it)
        else
            AddNewsInfoForm() {
                articles = articles + it
                showAddScreen = false
            }
    }
}
