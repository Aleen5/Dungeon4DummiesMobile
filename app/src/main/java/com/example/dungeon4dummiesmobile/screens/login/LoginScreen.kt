package com.example.dungeon4dummiesmobile.views

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontListFontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.dungeon4dummiesmobile.models.UsersModel
import com.example.dungeon4dummiesmobile.screens.shared.TopBar
import com.example.dungeon4dummiesmobile.ui.theme.DDARKBROWN
import com.example.dungeon4dummiesmobile.ui.theme.DDLIGHTBROWN
import org.intellij.lang.annotations.JdkConstants

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
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
         {
             Box(
                 Modifier.background(DDLIGHTBROWN)

             ) {
                 Column(
                     horizontalAlignment = Alignment.CenterHorizontally,
                 ) {
                     Text(
                         text = "Dungeon4Dummies",
                         color = DDARKBROWN,
                         fontFamily = FontFamily.Cursive,
                         fontWeight = FontWeight.Bold,
                         modifier = Modifier.padding(25.dp)
                     )

                     LoginTextField(user, onValueChange = {user = it})
                     PasswordTextField(password, onValueChange = {password = it})
                 }
             }

             /* Lazy Column DJ Example
            LazyColumn() {
                items(UsersModel.usersArray) { user ->
                    Text(text = user.username)
                }
            }

              */
        }

    }
}

@Composable
fun LoginTextField(user: String, onValueChange:(textUser: String) -> Unit) {
    TextField(
        value = user,
        onValueChange = { onValueChange(it) },
        label = { Text("User") },
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun PasswordTextField(password: String, onValueChange:(textPassword: String) -> Unit) {
    TextField(
        value = password,
        onValueChange = { onValueChange(it) },
        label = { Text("Password") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun LoginButton(user: String, password: String) {

}