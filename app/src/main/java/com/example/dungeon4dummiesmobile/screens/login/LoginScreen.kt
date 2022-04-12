package com.example.dungeon4dummiesmobile.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.dungeon4dummiesmobile.models.UsersModel
import com.example.dungeon4dummiesmobile.screens.shared.TopBar

@Composable
fun LoginScreen() {
    var user by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }
    
    Scaffold(

        topBar = { TopBar(barText = "Login")},

    ) { contentPadding ->
        Column() {
            LoginTextField(user, onValueChange = {user = it})
            PasswordTextField(password, onValueChange = {password = it})

            LazyColumn() {
                items(UsersModel.usersArray) { user ->
                    Text(text = user.username)
                }
            }
        }

    }
}

@Composable
fun LoginTextField(user: String, onValueChange:(textUser: String) -> Unit) {
    TextField(
        value = user,
        onValueChange = { onValueChange(it) },
        label = { Text("User") }
    )
}

@Composable
fun PasswordTextField(password: String, onValueChange:(textPassword: String) -> Unit) {
    TextField(
        value = password,
        onValueChange = { onValueChange(it) },
        label = { Text("Password") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}