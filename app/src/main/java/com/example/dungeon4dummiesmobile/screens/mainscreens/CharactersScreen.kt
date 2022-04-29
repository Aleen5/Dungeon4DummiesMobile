package com.example.dungeon4dummiesmobile.screens.mainscreens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.dungeon4dummiesmobile.screens.shared.BottomBar
import com.example.dungeon4dummiesmobile.screens.shared.Drawer
import com.example.dungeon4dummiesmobile.screens.shared.TopBarExtended
import com.example.dungeon4dummiesmobile.ui.theme.MAINCOLOR
import com.example.dungeon4dummiesmobile.viewModels.CharactersViewModel
import com.example.dungeon4dummiesmobile.viewModels.UsersViewModel

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
                charactersList = charactersViewModel.charactersModelListResponse
            }
        }
    }

    Scaffold(
        topBar = { TopBarExtended(barText = "Characters", scope = scope, scaffoldState = scaffoldState) },
        bottomBar = { BottomBar(navController, username!!) },
        drawerGesturesEnabled = true,
        drawerContent = {
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController, username!!)
        }
    ) {
        Spacer(modifier = Modifier.padding(top = 40.dp))
        if (!charactersList.isEmpty()) {

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(charactersList) { character ->
                    CharacterCard(character)
                }
            }
        } else {
            //Text(text = "Nothing to show here.", modifier = Modifier.padding(top = 40.dp))
        }
    }
}

@Composable
fun CharacterCard(character: CharactersModel) {
    val context = LocalContext.current
    Card(
        modifier = Modifier.fillMaxWidth().padding(top = 5.dp, bottom = 15.dp).clickable {
            Toast.makeText(context, "${character.name} ${character.surname}", Toast.LENGTH_SHORT).show()
            // NAVEGAR AQUI A VENTANA DE CHARACTER
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Image(
                painter = painterResource(id = R.drawable.dungeon4dummieslogo),
                "Character_Avatar",
                modifier = Modifier.width(80.dp).height(80.dp))
            Column {
                Text("${character.alias}")
                Text("Lvl ${character.level} ${character.character_class} ${character.archetype}")
                Text("Status: ${character.status} HP: ${character.current_hp}/${character.max_hp} Mana: ${character.current_mana}/${character.max_mana}")
            }
        }
    }

}
