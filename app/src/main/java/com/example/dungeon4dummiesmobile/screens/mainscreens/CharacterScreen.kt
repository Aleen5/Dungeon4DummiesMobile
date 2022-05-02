package com.example.dungeon4dummiesmobile.screens.mainscreens

import StatsModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dungeon4dummiesmobile.screens.shared.BottomBar
import com.example.dungeon4dummiesmobile.screens.shared.Drawer
import com.example.dungeon4dummiesmobile.screens.shared.TopBarExtended
import com.example.dungeon4dummiesmobile.viewModels.CharactersViewModel

@Composable
fun CharacterScreen(navController: NavController, username: String, characterID: String) {
    val charactersViewModel = viewModel(CharactersViewModel::class.java)
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    var character by remember {
        mutableStateOf(charactersViewModel.charactersModel)
    }

    var getData by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = getData) {
        if (getData) {
            getData = false
            charactersViewModel.get1Character(characterID) {
                character = it!!
            }
        }
    }

    Scaffold(
        topBar = { TopBarExtended(barText = "Character: ${character.alias}", scope = scope, scaffoldState = scaffoldState) },
        bottomBar = { BottomBar(navController, username) },
        drawerGesturesEnabled = true,
        drawerContent = {
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController, username)
        }
    ) {
        Spacer(modifier = Modifier.padding(top = 40.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                item { StatsText(statName = "Name", statValue = character.name) }
                item { StatsText(statName = "Surname", statValue = character.surname) }
                item { StatsText(statName = "Alias", statValue = character.alias) }
                item { StatsText(statName = "Status", statValue = character.status) }
                item { StatsText(statName = "Race", statValue = character.race) }
                item { StatsText(statName = "Alignment", statValue = character.alignment) }
                item { StatsNumber(statName = "Level", statValue = character.level) }
                item { StatsNumber(statName = "Exp", statValue = character.exp) }
                item { StatsNumber(statName = "Level", statValue = character.level) }
                item { StatsText(statName = "Class", statValue = character.character_class) }
                item { StatsText(statName = "Archetype", statValue = character.archetype) }
                item { StatText(statName = "Inventory")}
                items(character.inventory) { item ->
                    StatsArrayItem(statName = item)
                }
                item { StatText(statName = "Attacks & Sorceries")}
                items(character.attacks_sorceries) { attack ->
                    StatsArrayItem(statName = attack)
                }
                item { StatText(statName = "Stats")}
                item { SubstatsList(substats = character.stats)}
                item { StatsText(statName = "HP", statValue = "${character.current_hp}/${character.max_hp}") }
                item { StatsText(statName = "MP", statValue = "${character.current_mana}/${character.max_mana}") }
                item { StatText(statName = "Adventure Journal")}
                items(character.adventure_journal) { tale ->
                    StatsArrayItem(statName = tale)
                }
                item { StatText(statName = "Features & Traits")}
                items(character.features_traits) { feat ->
                    StatsArrayItem(statName = feat)
                }
                item { StatsNumber(statName = "Death Saves", statValue = character.death_saves) }
                item { StatsText(statName = "Backstory", statValue = character.backstory) }
                item { StatsText(statName = "Ideals", statValue = character.ideals) }
                item { StatsText(statName = "Proficiencies", statValue = character.proficiencies) }
                item { StatsText(statName = "Flaws", statValue = character.flaws) }
                item { StatsText(statName = "Personality Traits", statValue = character.personality_traits) }
                item { StatsText(statName = "Bonds", statValue = character.bonds) }
                item { StatsNumber(statName = "Archetype", statValue = character.age) }
                item { StatsText(statName = "Languages", statValue = character.languages) }
                item { StatsText(statName = "Avatar (link)", statValue = character.avatar) }
                item { Spacer(modifier = Modifier.height(25.dp))}
            }
        }
    }
}

@Composable
fun StatsText(statName: String, statValue: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "$statName:",
            fontWeight = FontWeight.Bold
        )
        Text(
            text = statValue,
            fontStyle = FontStyle.Italic
        )
    }
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
fun StatsNumber(statName: String, statValue: Int) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = "$statName:",
            fontWeight = FontWeight.Bold
        )
        Text(
            text = statValue.toString(),
            fontStyle = FontStyle.Italic
        )
    }
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
fun StatText(statName: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = "$statName:",
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun StatsArrayItem(statName: String) {
    Row() {
        Spacer(modifier = Modifier.width(5.dp))
        Icon(Icons.Filled.List, contentDescription = "")
        Text(
            text = statName,
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
fun SubstatsList(substats: StatsModel) {
    StatsArrayItem(statName = "Armor Class: ${substats.ArmorClass}")
    StatsArrayItem(statName = "Initiative: ${substats.Initiative}")
    StatsArrayItem(statName = "Strength: ${substats.Strength}")
    StatsArrayItem(statName = "Dexterity: ${substats.Dexterty}")
    StatsArrayItem(statName = "Constitution: ${substats.Constitution}")
    StatsArrayItem(statName = "Intelligence: ${substats.Intelligence}")
    StatsArrayItem(statName = "Wisdom: ${substats.Wisdom}")
    StatsArrayItem(statName = "Charisma: ${substats.Charisma}")
    StatsArrayItem(statName = "Acrobatics: ${substats.Acrobatics}")
    StatsArrayItem(statName = "Athletics: ${substats.Athletics}")
    StatsArrayItem(statName = "Deception: ${substats.Deception}")
    StatsArrayItem(statName = "History: ${substats.History}")
    StatsArrayItem(statName = "Insight: ${substats.Insight}")
    StatsArrayItem(statName = "Intimidation: ${substats.Intimidation}")
    StatsArrayItem(statName = "Performance: ${substats.Performance}")
    StatsArrayItem(statName = "Medicine: ${substats.Medicine}")
    StatsArrayItem(statName = "Nature: ${substats.Nature}")
    StatsArrayItem(statName = "Perception: ${substats.Perception}")
    StatsArrayItem(statName = "Persuasion: ${substats.Persuasion}")
    StatsArrayItem(statName = "Religion: ${substats.Religion}")
    StatsArrayItem(statName = "Stealth: ${substats.Stealth}")
    StatsArrayItem(statName = "Survival: ${substats.Survival}")
    StatsArrayItem(statName = "Animal Handling: ${substats.AnimalHandling}")
}