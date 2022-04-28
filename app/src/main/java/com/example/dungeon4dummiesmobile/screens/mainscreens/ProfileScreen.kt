package com.example.dungeon4dummiesmobile.screens.mainscreens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dungeon4dummiesmobile.screens.shared.BottomBar
import com.example.dungeon4dummiesmobile.screens.shared.Drawer
import com.example.dungeon4dummiesmobile.screens.shared.TopBarExtended

@Composable
fun ProfileScreen(navController: NavController, username: String) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = { TopBarExtended(barText = "Profile", scope = scope, scaffoldState = scaffoldState) },
        bottomBar = { BottomBar(navController, "TODO") },
        drawerGesturesEnabled = true,
        drawerContent = {
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController, username!!)
        }
    ){
        username?.let { Text(text = it, modifier = Modifier.padding(top = 40.dp)) }
    }
    //username?.let { Text(text = it, modifier = Modifier.padding(top = 40.dp)) }
}