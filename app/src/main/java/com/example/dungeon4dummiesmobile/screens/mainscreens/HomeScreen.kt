package com.example.dungeon4dummiesmobile.screens.mainscreens

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.example.dungeon4dummiesmobile.screens.shared.BottomBar
import com.example.dungeon4dummiesmobile.screens.shared.Drawer
import com.example.dungeon4dummiesmobile.screens.shared.TopBarExtended

@Composable
fun HomeScreen(navController: NavController, username: String?) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = { TopBarExtended(barText = "Home", scope = scope, scaffoldState = scaffoldState) },
        bottomBar = { BottomBar(navController) },
        drawerGesturesEnabled = true,
        drawerContent = {
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController)
        }
    ){
    }
    //username?.let { Text(text = it, modifier = Modifier.padding(top = 40.dp)) }
}