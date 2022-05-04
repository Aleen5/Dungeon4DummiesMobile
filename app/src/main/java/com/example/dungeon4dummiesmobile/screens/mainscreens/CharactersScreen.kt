package com.example.dungeon4dummiesmobile.screens.mainscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dungeon4dummiesmobile.R
import com.example.dungeon4dummiesmobile.models.CharactersModel
import com.example.dungeon4dummiesmobile.navigation.AppScreens
import com.example.dungeon4dummiesmobile.screens.shared.BottomBar
import com.example.dungeon4dummiesmobile.screens.shared.Drawer
import com.example.dungeon4dummiesmobile.screens.shared.TopBarExtended
import com.example.dungeon4dummiesmobile.viewModels.CharactersViewModel

@Composable
fun CharactersScreen(navController: NavController, username: String) {
    val charactersViewModel = viewModel(CharactersViewModel::class.java)
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    var charactersList by remember {
        mutableStateOf(charactersViewModel.charactersModelListResponse)
    }

    var getData by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = getData) {
        if (getData) {
            getData = false
            charactersViewModel.getUsersCharacters(username) {
                charactersList = it
            }
        }
    }

    Scaffold(
        topBar = { TopBarExtended(barText = "Characters", scope = scope, scaffoldState = scaffoldState) },
        bottomBar = { BottomBar(navController, username) },
        drawerGesturesEnabled = true,
        drawerContent = {
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController, username)
        },
        scaffoldState = scaffoldState
    ) {
        Spacer(modifier = Modifier.padding(top = 40.dp))
        if (!charactersList.isEmpty()) {

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(charactersList) { character ->
                    CharacterCard(navController, character)
                }
                item { CreateCharacterScreenButton(navController = navController, username = username) }
                item { Spacer(modifier = Modifier.height(40.dp)) }
            }
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Nothing to show here. Maybe create a new character?", modifier = Modifier.padding(top = 40.dp))
                CreateCharacterScreenButton(navController = navController, username = username)
            }
        }
    }
}

@Composable
fun CharacterCard(navController: NavController, character: CharactersModel) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, bottom = 15.dp)
            .clickable {
                navController.navigate(route = AppScreens.CharacterScreen.route + "/${character.owner}/${character.id}")
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Image(
                painter = painterResource(id = R.drawable.dungeon4dummieslogo),
                "Character_Avatar",
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp))
            Column {
                Text("${character.alias}")
                Text("Lvl ${character.level} ${character.character_class} ${character.archetype}")
                Text("Status: ${character.status} HP: ${character.current_hp}/${character.max_hp} Mana: ${character.current_mana}/${character.max_mana}")
            }
        }
    }
}

@Composable
fun CreateCharacterScreenButton(navController: NavController, username: String) {
    Button(onClick = {
        navController.navigate(route = AppScreens.CharacterCreationScreen.route + "/$username")
    }) {
        Text("Create a new character")
    }
}
