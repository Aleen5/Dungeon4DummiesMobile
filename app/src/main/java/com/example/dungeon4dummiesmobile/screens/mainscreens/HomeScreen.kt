package com.example.dungeon4dummiesmobile.screens.mainscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dungeon4dummiesmobile.R
import com.example.dungeon4dummiesmobile.screens.shared.BottomBar
import com.example.dungeon4dummiesmobile.screens.shared.Drawer
import com.example.dungeon4dummiesmobile.screens.shared.TopBarExtended
import com.example.dungeon4dummiesmobile.viewModels.UsersViewModel
import kotlin.random.Random

@Composable
fun HomeScreen(navController: NavController, username: String) {
    val usersViewModel = viewModel(UsersViewModel::class.java)
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var user by remember {
        mutableStateOf(usersViewModel.user)
    }

    val tips = listOf("You don't have to know every rule", "Don't plan too much", "Things aren't written in stone",
        "The rules are only a guide", "Don't attack your Dungeon Master!", "Always keep close your player's handbook",
        "Take your time to write notes", "Always roleplay according to your character's backstory and behavior",
        "Meta-gaming is a sin", "People die if they are killed", "What happens, happens. And there's no turning back.")

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
        Column(modifier = Modifier.padding(top = 10.dp, end = 10.dp, start = 10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Welcome back, ")
                Text(text = "${user.username}!", fontSize = 18.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) { Text(text = "Here's your random D&D tip:", fontSize = 18.sp) }
            Spacer(modifier = Modifier.height(70.dp))
            Row() { Text(text = "${tips[Random.nextInt(tips.size)]}", textAlign = TextAlign.Center ,fontSize = 34.sp, fontFamily = FontFamily.Cursive)}

            Spacer(modifier = Modifier.height(30.dp))
            Image(painterResource(id = R.drawable.dungeon4dummieslogotextless), contentDescription = "",
            modifier = Modifier.width(120.dp).height(120.dp))
        }



    }
}