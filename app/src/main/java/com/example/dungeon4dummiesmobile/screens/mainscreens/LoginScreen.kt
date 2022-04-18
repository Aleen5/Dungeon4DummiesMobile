package com.example.dungeon4dummiesmobile.screens.mainscreens

import android.widget.Toast
import androidx.compose.foundation.layout.*
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
                     InputTextField("Username", user, onValueChange = {user = it})
                     PasswordTextField("Password", password, onValueChange = {password = it})
                     Row(
                     ) {
                         LoginButton(user = user, password = password, navController = navController)
                         Spacer(modifier = Modifier.width(40.dp))
                         NavRegisterButton(navController = navController)
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
fun LoginButton(user: String, password: String, navController: NavController) {
    val context = LocalContext.current
    Button(
        modifier = Modifier
            .width(100.dp)
            .padding(vertical = 15.dp),
        onClick = {

            if (user.equals("") || user.equals(" ")) {
                Toast.makeText(context, "Empty login", Toast.LENGTH_SHORT).show()
                return@Button
            }

            if (password.equals("") || password.equals(" ")) {
                Toast.makeText(context, "Empty password", Toast.LENGTH_SHORT).show()
                return@Button
            }

            var currentUser = UsersModel("u1", user, password, "Lex", "Rodr", "rodr@gmail.dev")
            navController.navigate(route = AppScreens.HomeScreen.route + "/${currentUser.username}")
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = SECONDARYCOLOR)
    ) {
        Text(text = "Log in")
    }
}

@Composable
fun NavRegisterButton(navController: NavController) {
    val context = LocalContext.current
    Button(
        modifier = Modifier
            .width(100.dp)
            .padding(vertical = 15.dp),
        onClick = {
            navController.navigate(route = AppScreens.RegisterScreen.route)
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = SECONDARYCOLOR)
    ) {
        Text(text = "Register")
    }
}