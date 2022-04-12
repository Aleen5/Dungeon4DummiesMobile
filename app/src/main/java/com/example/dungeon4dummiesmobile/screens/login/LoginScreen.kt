package com.example.dungeon4dummiesmobile.screens.login

import android.os.UserManager
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.dungeon4dummiesmobile.models.UsersModel
import com.example.dungeon4dummiesmobile.navigation.AppScreens
import com.example.dungeon4dummiesmobile.screens.shared.LogoAsset
import com.example.dungeon4dummiesmobile.screens.shared.TopBar
import com.example.dungeon4dummiesmobile.ui.theme.MAINCOLOR
import com.example.dungeon4dummiesmobile.ui.theme.SECONDARYCOLOR
import com.google.gson.Gson

@Composable
fun LoginScreen(navController: NavController) {
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
             Card(
                 modifier = Modifier.padding(vertical = 50.dp),
                 backgroundColor = MAINCOLOR

             ) {
                 Column(
                     horizontalAlignment = Alignment.CenterHorizontally,
                 ) {
                     LogoAsset()
                     LoginTextField(user, onValueChange = {user = it})
                     PasswordTextField(password, onValueChange = {password = it})
                     LoginButton(user = user, password = password, navController = navController)
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
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier.padding(16.dp),
    )
}

@Composable
fun LoginButton(user: String, password: String, navController: NavController) {
    Button(
        modifier = Modifier.width(100.dp).padding(vertical = 15.dp),
        onClick = {
            var currentUser = UsersModel("u1", user, password, "Lex", "Rodr", "rodr@gmail.dev")
            navController.navigate(route = AppScreens.HomeScreen.route + "/${currentUser.username}")
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = SECONDARYCOLOR)
    ) {
        Text(text = "Log in")
    }
}