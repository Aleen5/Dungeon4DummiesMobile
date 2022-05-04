package com.example.dungeon4dummiesmobile.screens.mainscreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dungeon4dummiesmobile.navigation.AppScreens
import com.example.dungeon4dummiesmobile.screens.shared.*
import com.example.dungeon4dummiesmobile.ui.theme.MAINCOLOR
import com.example.dungeon4dummiesmobile.ui.theme.SECONDARYCOLOR
import com.example.dungeon4dummiesmobile.viewModels.UsersViewModel

@Composable
fun ProfileScreen(navController: NavController, username: String) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val usersViewModel = viewModel(UsersViewModel::class.java)

    var user by remember {
        mutableStateOf(usersViewModel.user)
    }

    var getData by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = getData) {
        if (getData) {
            getData = false
            usersViewModel.get1User(username) {
                user = usersViewModel.user
            }
        }
    }

    Scaffold(
        topBar = { TopBarExtended(barText = "Profile", scope = scope, scaffoldState = scaffoldState) },
        bottomBar = { BottomBar(navController, username) },
        drawerGesturesEnabled = true,
        drawerContent = {
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController, username)
        },
        scaffoldState = scaffoldState
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Card(
                modifier = Modifier.padding(vertical = 50.dp),
                backgroundColor = MAINCOLOR

            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    LogoAsset()
                    ProfileTextField(field = "Name", value = user.name)
                    ProfileTextField(field = "Surname", value = user.surname)
                    ProfileTextField(field = "Username", value = user.username)
                    ProfileTextField(field = "Email", value = user.email)
                    ProfilePwdField(field = "Password", value = user.password)
                    ProfileEditButton(navController = navController, username = username)
                }
            }
        }
    }
}

@Composable
fun ProfileTextField(field: String, value: String) {
    Row(modifier = Modifier.padding(start = 20.dp, end = 20.dp).fillMaxWidth()) {
        Text(text = "$field: ", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text(text = "$value", fontStyle = FontStyle.Italic, fontSize = 18.sp)
    }
}

@Composable
fun ProfilePwdField(field: String, value: String) {
    var pwdDots = ""
    for (i in 0..value.length)
        pwdDots += "*"

    Row(modifier = Modifier.padding(start = 20.dp, end = 20.dp).fillMaxWidth()) {
        Text(text = "$field: ", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text(text = "$pwdDots", fontStyle = FontStyle.Italic, fontSize = 18.sp)
    }
}

@Composable
fun ProfileEditButton(navController: NavController, username: String) {
    Row(modifier = Modifier.padding(start = 20.dp, end = 20.dp), horizontalArrangement = Arrangement.Center) {
        Button(
            modifier = Modifier
                .width(130.dp)
                .padding(vertical = 15.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = SECONDARYCOLOR),
            onClick = {
                navController.navigate(route = AppScreens.HomeScreen.route + "/${username}")
            }
        ) {
            Text(text = "Edit profile")
        }
    }
}