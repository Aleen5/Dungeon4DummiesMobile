package com.example.dungeon4dummiesmobile.screens.mainscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dungeon4dummiesmobile.models.CharactersModel
import com.example.dungeon4dummiesmobile.navigation.AppScreens
import com.example.dungeon4dummiesmobile.screens.shared.*
import com.example.dungeon4dummiesmobile.ui.theme.MAINCOLOR
import com.example.dungeon4dummiesmobile.viewModels.CharactersViewModel
import com.example.dungeon4dummiesmobile.viewModels.UsersViewModel

@Composable
fun CharacterCreationScreen(navController: NavController, username: String) {
    val usersViewModel = viewModel(UsersViewModel::class.java)
    val charactersViewModel = viewModel(CharactersViewModel::class.java)
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var user by remember {
        mutableStateOf(usersViewModel.user)
    }
    var character by remember {
        mutableStateOf(value = charactersViewModel.charactersModel)
    }
    var stats by remember {
        mutableStateOf(value = charactersViewModel.statsModel)
    }

    val races = listOf("Dragonborn", "Dwarf", "Elf", "Gnome", "Half-Elf", "Halfling", "Half-Orc",
        "Human", "Tiefling", "Orc of Exandria", "Leonin", "Satyr", "Fairy", "Harengon", "Owlin",
        "Aarakocra", "Genasi", "Goliath", "Aasimar", "Bugbear", "Firbolg", "Goblin", "Hobgoblin",
        "Kenku", "Kobold", "Lizardfolk", "Orc", "Tabaxi", "Triton", "Yuan-ti Pureblood",
        "Feral Tiefling", "Tortle", "Changeling", "Kalashtar", "Orc of Eberron", "Shifter",
        "Warforged", "Gith", "Centaur", "Loxodon", "Minotaur", "Simic Hybrid", "Vedalken", "Verdan",
        "Locathah", "Grung")

    val classes = listOf("Barbarian", "Bard", "Cleric", "Druid", "Fighter", "Monk", "Paladin", "Rogue",
        "Sorcerer", "Warlock", "Wizard", "Artificer", "Blood Hunter")

    var name by remember {
        mutableStateOf("")
    }
    var surname by remember {
        mutableStateOf("")
    }
    var alias by remember {
        mutableStateOf("")
    }
    val status by remember {
        mutableStateOf("Alive")
    }
    var race by remember {
        mutableStateOf("")
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
        topBar = { TopBarExtended(barText = "Character Creation", scope = scope, scaffoldState = scaffoldState) },
        bottomBar = { BottomBar(navController, username) },
        drawerGesturesEnabled = true,
        drawerContent = {
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController, username)
        },
        scaffoldState = scaffoldState
    ){
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
                    item { LogoAsset() }
                    item { InputTextField("Name", name, onValueChange = {name = it}) }
                    item { InputTextField("Surname", surname, onValueChange = {surname = it}) }
                    item { InputTextField("Alias", alias, onValueChange = {alias = it}) }
                    item { StringDropdownMenu(races, race, onValueChange = {race = it}) }
                }
            }
        }
    }
}

@Composable
fun StringDropdownMenu(list: List<String>, inValue:String, onValueChange:(textUser: String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val disabledValue = "B"
    var selectedIndex by remember { mutableStateOf(0) }
    Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
        Text(list[selectedIndex],modifier = Modifier.fillMaxWidth().clickable(onClick = { expanded = true }).background(
            Color.Gray))
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }

        ) {
            list.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    //onValueChange = { onValueChange(it) },
                    expanded = false
                }) {
                    val disabledText = if (s == disabledValue) {
                        " (Disabled)"
                    } else {
                        ""
                    }
                    Text(text = s + disabledText)
                }
            }
        }
    }
}
