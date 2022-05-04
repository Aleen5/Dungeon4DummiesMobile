package com.example.dungeon4dummiesmobile.screens.mainscreens

import android.util.Log
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dungeon4dummiesmobile.models.UsersModel
import com.example.dungeon4dummiesmobile.navigation.AppScreens
import com.example.dungeon4dummiesmobile.screens.shared.*
import com.example.dungeon4dummiesmobile.ui.theme.MAINCOLOR
import com.example.dungeon4dummiesmobile.ui.theme.SECONDARYCOLOR
import com.example.dungeon4dummiesmobile.viewModels.UsersViewModel

@Composable
fun RegisterScreen(navController: NavController) {
    val usersViewModel = viewModel(UsersViewModel::class.java)
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

        ) {
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
                        RegisterButton(navController, name, surname, email, username, password, repeatPassword, usersViewModel)}
                    }
                }
            }
        }
    }
}

@Composable
fun RegisterButton(navController: NavController, name: String, surname: String, email: String, username: String, password: String,
    repeatPassword: String, usersViewModel: UsersViewModel) {
    val context = LocalContext.current
    val showDialogLoading = remember {
        mutableStateOf(false)
    }

    Button(
        modifier = Modifier
            .width(100.dp)
            .padding(vertical = 15.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = SECONDARYCOLOR),
        onClick = {
            // Field checks
            if (name == "" || name == " " || name == null) {
                Toast.makeText(context, "Name cannot be empty", Toast.LENGTH_SHORT).show()
                return@Button
            } else if (surname == "" || surname == " " || surname == null) {
                Toast.makeText(context, "Surname cannot be empty", Toast.LENGTH_SHORT).show()
                return@Button
            } else if (email == "" || email == " " || email == null) {
                Toast.makeText(context, "Email cannot be empty", Toast.LENGTH_SHORT).show()
                return@Button
            } else if (username == "" || username == " " || username == null) {
            Toast.makeText(context, "Username cannot be empty", Toast.LENGTH_SHORT).show()
            return@Button
            } else if (password == "" || password == " " || password == null) {
                Toast.makeText(context, "Password cannot be empty", Toast.LENGTH_SHORT).show()
                return@Button
            } else if (repeatPassword == "" || repeatPassword == " " || repeatPassword == null) {
                Toast.makeText(context, "Repeat password cannot be empty", Toast.LENGTH_SHORT).show()
                return@Button
            } else if (password != repeatPassword) {
                Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@Button
            }

            showDialogLoading.value = true
            val rnds = (100000000..999999999).random().toString()
            val user = UsersModel(rnds, username, password, name, surname, email, mutableListOf(""))

            usersViewModel.get1User(username) { existingUser ->
                if (existingUser != null) {
                    Toast.makeText(context, "The username is taken", Toast.LENGTH_SHORT).show()

                } else {
                    usersViewModel.postUser(user)
                    Toast.makeText(context, "Registered successfully", Toast.LENGTH_SHORT).show()
                    navController.navigate(route = AppScreens.HomeScreen.route + "/${user.username}")
                }
            }
            showDialogLoading.value = false
            //navController.navigate(route = AppScreens.HomeScreen.route + "/${currentUser.username}")
        }
    ) {
        Text(text = "Register")
    }

    DialogLoading(showDialogLoading)
}