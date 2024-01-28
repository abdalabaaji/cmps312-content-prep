package com.cmps312.todolist.ui.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cmps312.todolist.model.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignInViewModel(application: Application) : AndroidViewModel(application) {
    val context = application
    private var auth = FirebaseAuth.getInstance()
    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> = _user
    var userRegistratedSuccessfully = MutableStateFlow(false)

    init {
        if (auth.currentUser != null) _user.value = User(auth.currentUser?.email!!, "")
    }

    fun registerUser(email: String, password: String) = viewModelScope.launch {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    userRegistratedSuccessfully.value = true
                    Toast.makeText(
                        context,
                        " Successfully registered user",
                        Toast.LENGTH_SHORT,
                    ).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        context,
                        " Failed to register user.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

    fun signIn(email: String, password: String) = viewModelScope.launch {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithEmail:success")
                    _user.value = User(auth.currentUser?.email!!, "")

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        context,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    _user.value = null
                }
            }
    }

    fun signOut() = viewModelScope.launch {
        auth.signOut()
        _user.value = null
    }

    override fun onCleared() {
        super.onCleared()
        auth.signOut()
    }

}