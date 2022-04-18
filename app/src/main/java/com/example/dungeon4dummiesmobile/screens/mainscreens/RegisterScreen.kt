package com.example.dungeon4dummiesmobile.screens.mainscreens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dungeon4dummiesmobile.models.UsersModel
import com.example.dungeon4dummiesmobile.navigation.AppScreens
import com.example.dungeon4dummiesmobile.screens.shared.InputTextField
import com.example.dungeon4dummiesmobile.screens.shared.LogoAsset
import com.example.dungeon4dummiesmobile.screens.shared.PasswordTextField
import com.example.dungeon4dummiesmobile.screens.shared.TopBar
import com.example.dungeon4dummiesmobile.ui.theme.MAINCOLOR
import com.example.dungeon4dummiesmobile.ui.theme.SECONDARYCOLOR

@Composable
fun RegisterScreen(navController: NavController) {
    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var repeatPassword by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var name by remember {
        mutableStateOf("")
    }

    var surname by remember {
        mutableStateOf("")
    }

    var characters by remember {
        mutableStateOf(mutableListOf(""))
    }

    Scaffold(

        topBar = { TopBar(barText = "Register")},

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
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item { LogoAsset()}
                    item { InputTextField("Name", name, onValueChange = {name = it})}
                    item { InputTextField("Surname", surname, onValueChange = {surname = it})}
                    item { InputTextField("E-mail", email, onValueChange = {email = it})}
                    item { InputTextField("Username", username, onValueChange = {username = it})}
                    item { PasswordTextField("Password", password, onValueChange = {password = it})}
                    item { PasswordTextField("Repeat password", repeatPassword, onValueChange = {repeatPassword = it})}
                    item { Row {
                        RegisterButton(navController = navController, name, surname, email, username, password, repeatPassword) }
                    }
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
fun RegisterButton(navController: NavController, name: String, surname: String, email: String, username: String, password: String,
    repeatPassword: String) {
    val context = LocalContext.current
    Button(
        modifier = Modifier
            .width(100.dp)
            .padding(vertical = 15.dp),
        onClick = {
            Toast.makeText(context, "Registered successfully", Toast.LENGTH_SHORT)

            var currentUser = UsersModel("u1", username, password, name, surname, email)
            navController.navigate(route = AppScreens.HomeScreen.route + "/${currentUser.username}")
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = SECONDARYCOLOR)
    ) {
        Text(text = "Register")
    }
}