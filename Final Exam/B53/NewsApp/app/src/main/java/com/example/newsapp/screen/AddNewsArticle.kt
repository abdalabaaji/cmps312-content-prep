package com.example.newsapp.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.model.Article
import com.example.newsapp.ui.theme.NewsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewsInfoForm(
    onAddNewsInfo: (Article) -> Unit
) {
    var title by remember { mutableStateOf(TextFieldValue()) }
    var article by remember { mutableStateOf(TextFieldValue()) }
    var image by remember { mutableStateOf(TextFieldValue()) }
    var category by remember { mutableStateOf(TextFieldValue()) }
    var author by remember { mutableStateOf(TextFieldValue()) }
    var date by remember { mutableStateOf(TextFieldValue()) }

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
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                "Add News Info",
                fontSize = 30.sp,
                letterSpacing = 3.sp,
                textDecoration = TextDecoration.Underline
            )

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Title") }
            )

            // Article
            OutlinedTextField(
                value = article,
                onValueChange = { article = it },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Article") },
                maxLines = 10
            )

            // Image
            OutlinedTextField(
                value = image,
                onValueChange = { image = it },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Image Name") }
            )

            // Category
            CategoryDropDown("NONE"){
                category = TextFieldValue(it)
            }

            // Author
            OutlinedTextField(
                value = author,
                onValueChange = { author = it },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Author") }
            )

            // Date
            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Date") }
            )
            val context = LocalContext.current
            Button(
                onClick = {
                    val article = Article(
                        title.text, article.text, image.text, category.text, author.text, date.text
                    )
                    onAddNewsInfo(article)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text("Add Article")
            }
        }
    }
}

@Preview
@Composable
fun AddNewsInfoForm() {
    val context = LocalContext.current
    NewsAppTheme {
        AddNewsInfoForm() {
            Toast.makeText(context, it.author, Toast.LENGTH_SHORT).show()
        }
    }
}
