package com.example.dungeon4dummiesmobile.screens.mainscreens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dungeon4dummiesmobile.models.UsersModel
import com.example.dungeon4dummiesmobile.navigation.AppScreens
import com.example.dungeon4dummiesmobile.screens.shared.BottomBar
import com.example.dungeon4dummiesmobile.screens.shared.Drawer
import com.example.dungeon4dummiesmobile.screens.shared.TopBarExtended
import com.example.dungeon4dummiesmobile.viewModels.CharactersViewModel
import com.example.dungeon4dummiesmobile.viewModels.UsersViewModel
import kotlinx.coroutines.currentCoroutineContext

@Composable
fun HomeScreen(navController: NavController, username: String) {
    val usersViewModel = viewModel(UsersViewModel::class.java)
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
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
        topBar = { TopBarExtended(barText = "Home", scope = scope, scaffoldState = scaffoldState) },
        bottomBar = { BottomBar(navController, username) },
        drawerGesturesEnabled = true,
        drawerContent = {
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController, username)
        },
        scaffoldState = scaffoldState
    ){
        Text(text = "Welcome back, ${user.username}.")
    }
}