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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dungeon4dummiesmobile.models.UsersModel
import com.example.dungeon4dummiesmobile.navigation.AppScreens
import com.example.dungeon4dummiesmobile.screens.shared.BottomBar
import com.example.dungeon4dummiesmobile.screens.shared.Drawer
import com.example.dungeon4dummiesmobile.screens.shared.TopBarExtended
import com.example.dungeon4dummiesmobile.viewModels.UsersViewModel
import kotlinx.coroutines.currentCoroutineContext

@Composable
fun HomeScreen(navController: NavController, username: String) {
    val usersViewModel = viewModel(UsersViewModel::class.java)
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
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
        bottomBar = { BottomBar(navController, username!!) },
        drawerGesturesEnabled = true,
        drawerContent = {
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController, username!!)
        }
    ){
        Text(text = user.username)
        LogButton(usersViewModel, username)

    }
    //username?.let { Text(text = it, modifier = Modifier.padding(top = 40.dp)) }
}

@Composable
fun HelloContent(user: UsersModel, onUserChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(top = 100.dp)) {
        Text(
            text = "Hello, ${user.username}"
        )
        OutlinedTextField(
            value = user.username,
            onValueChange = onUserChange,
            label = { Text("Username")}
        )
    }
}

@Composable
fun LogButton(usersViewModel: UsersViewModel, user: String?) {
    val context = LocalContext.current
    Button(
        onClick = {
            usersViewModel.get1User(user!!) { currentUser ->
                if (currentUser != null) {
                    Toast.makeText(context, currentUser.email, Toast.LENGTH_SHORT).show()
                }
                else if(currentUser == null){
                    Toast.makeText(context, "Incorrect username", Toast.LENGTH_SHORT).show()
                    Log.d("XD", "BAD")
                }
                else {
                    Toast.makeText(context, "Unexpected login error", Toast.LENGTH_SHORT).show()
                }
            }

            Toast.makeText(context, user, Toast.LENGTH_SHORT).show()
        },
        modifier = Modifier.padding(top = 80.dp)
    ) {

    }
}

// PREGUNTAR A SAINERO
// Como actualizar un composable con nuevos datos luego de haberlo creado?
// Campos que faltan en el plan económico