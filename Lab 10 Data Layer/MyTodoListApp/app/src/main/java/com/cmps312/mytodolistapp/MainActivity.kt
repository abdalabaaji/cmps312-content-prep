package com.cmps312.mytodolistapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmps312.mytodolistapp.ui.theme.MyTodoListAppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTodoListAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Serializable
data class Todo(
    val task: String,
    @DocumentId val id: String = "",
) {
    constructor() : this("")
}
data class LoginUiState(
    val email: String = "",
    val password: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val db = FirebaseFirestore.getInstance()
    var auth: FirebaseAuth = Firebase.auth
    var currentUser = auth.getCurrentUser()

    var uiState by remember {
        mutableStateOf(LoginUiState())
    }
    val todo = Todo("Do the Lab")
    val context = LocalContext.current
    val todosRef = db.collection("todos")
    var todos = remember { mutableStateListOf<Todo>() }


    var generatedId by remember {
        mutableStateOf("")
    }


//    register a listener
    todosRef.addSnapshotListener { snapshot, e ->
        if (e != null)
            return@addSnapshotListener
        todos.clear()
        snapshot!!.forEach { doc ->
            run {
                todos.add(doc.toObject(Todo::class.java))
            }
        }

    }

    Column {
        LazyColumn {
            items(todos) {
                Row(Modifier.padding(10.dp)) {

                    Text(text = " ${it.task} ${it.id}", modifier = Modifier.weight(1f))
                    Button(onClick = { todosRef.document(it.id).delete() }) {
                        Text(text = "Delete Todo")
                    }

                }
            }
        }
        Text(
            text = "Hello $name!",
            modifier = modifier
        )

        Button(onClick = {
            val todo = Todo("Do the Lab")
            generatedId = todo.id

            todosRef
                .add(Todo("Todo Item Number ${LocalDateTime.now()}"))
                .addOnSuccessListener {
                    Toast.makeText(context, "Added to DB", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "Did Not Add to DB ${e.toString()}", Toast.LENGTH_SHORT)
                        .show()
                    Log.e("ERROR OF APP", e.stackTraceToString())
                }
        }) {
            Text(text = "Add Todo")
        }
        Button(onClick = {
            db.collection("todos").get()
        }) {
            Text(text = "Get Todos")
        }
        val email by remember {
            mutableStateOf("")
        }
        val password by remember {
            mutableStateOf("")
        }
        OutlinedTextField(value = "Enter Email", onValueChange = {})
        OutlinedTextField(value = "Enter Password", onValueChange = {})

        Button(onClick = {

        }) {
            Text(text = "Register User")
        }

        Button(onClick = { /*TODO*/ }) {
            Text(text = "Login")
        }

        Button(onClick = { /*TODO*/ }) {
            Text(text = "Logout")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyTodoListAppTheme {
        Greeting("Android")
    }
}