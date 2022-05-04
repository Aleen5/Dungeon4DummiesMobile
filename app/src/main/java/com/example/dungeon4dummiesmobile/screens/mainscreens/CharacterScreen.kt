package com.example.dungeon4dummiesmobile.screens.mainscreens

import StatsModel
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dungeon4dummiesmobile.R
import com.example.dungeon4dummiesmobile.models.CharactersModel
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

    var spoilerlessCharacter by remember {
            mutableStateOf(CharactersModel(character.id, character.id, "Spoily", "Spoiler",  character.alias, "Spoiler",
            character.race, "The alignment of a spoily man", character.level, character.exp, character.character_class,
            character.archetype, character.stats, 55555, 55555, 55555, 55555, 55555,
            mutableListOf("Spoiler", "Spoiler"), character.attacks_sorceries, mutableListOf("Spoiler", "Spoiler"),151123, mutableListOf("Spoiler Item 1", "Spoiler Item 2"), "The spoiled backstory is not a good backstory",
            "Those ideals must be a huge spoiler", "Proficient in spoilerness", "", "Spoling",
        "Can't resist spoilers", "Bond negative with spoilers", "Spoilven", 5661612, character.owner))
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
                item { StatText(statName = "Inventory", painterResource(id = R.drawable.inventory))}
                items(character.inventory) { item ->
                    StatsArrayItem(statName = item, painterResource(id = R.drawable.bag))
                }
                item { StatText(statName = "Attacks & Sorceries")}
                items(character.attacks_sorceries) { attack ->
                    StatsArrayItem(statName = attack, painterResource(id = R.drawable.book))
                }
                item { StatText(statName = "Stats")}
                item { SubstatsList(substats = character.stats)}
                item { StatsText(statName = "HP", statValue = "${character.current_hp}/${character.max_hp}") }
                item { StatsText(statName = "MP", statValue = "${character.current_mana}/${character.max_mana}") }
                item { StatText(statName = "Adventure Journal")}
                items(character.adventure_journal) { tale ->
                    StatsArrayItem(statName = tale, painterResource(id = R.drawable.notebook))
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
                item { Spacer(modifier = Modifier.height(40.dp))}
            }
        }
    }
}

@Composable
fun StatsText(statName: String, statValue: String, painter: Painter = painterResource(R.drawable.list)) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "$statName:  ",
            fontWeight = FontWeight.Bold,
            color = Color.Yellow
        )
        Text(
            text = statValue,
            fontStyle = FontStyle.Italic
        )
    }
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
fun StatsNumber(statName: String, statValue: Int, painter: Painter = painterResource(R.drawable.list)) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = "$statName:  ",
            fontWeight = FontWeight.Bold,
            color = Color.Yellow
        )
        Text(
            text = statValue.toString(),
            fontStyle = FontStyle.Italic
        )
    }
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
fun StatText(statName: String, painter: Painter = painterResource(R.drawable.list)) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = "$statName:   ",
            fontWeight = FontWeight.Bold,
            color = Color.Yellow
        )
    }
}

@Composable
fun StatsArrayItem(statName: String, painter: Painter = painterResource(R.drawable.list)) {
    Row() {
        Spacer(modifier = Modifier.width(5.dp))
        Icon(painter, contentDescription = "")
        Spacer(modifier = Modifier.width(3.dp))
        Text(
            text = statName,
            fontStyle = FontStyle.Italic,
            color = Color.White
        )
    }
}

@Composable
fun SubstatsList(substats: StatsModel) {
    StatsArrayItem(statName = "Armor Class: ${substats.ArmorClass}", painterResource(id = R.drawable.shield))
    StatsArrayItem(statName = "Initiative: ${substats.Initiative} ${modifier(substats.Initiative)}", painterResource(id = R.drawable.initiative))
    StatsArrayItem(statName = "Strength: ${substats.Strength} ${modifier(substats.Strength)}", painterResource(id = R.drawable.strength))
    StatsArrayItem(statName = "Dexterity: ${substats.Dexterty} ${modifier(substats.Dexterty)}", painterResource(id = R.drawable.karate))
    StatsArrayItem(statName = "Constitution: ${substats.Constitution} ${modifier(substats.Constitution)}", painterResource(id = R.drawable.heart))
    StatsArrayItem(statName = "Intelligence: ${substats.Intelligence} ${modifier(substats.Intelligence)}", painterResource(id = R.drawable.idea))
    StatsArrayItem(statName = "Wisdom: ${substats.Wisdom} ${modifier(substats.Wisdom)}", painterResource(id = R.drawable.brain))
    StatsArrayItem(statName = "Charisma: ${substats.Charisma} ${modifier(substats.Charisma)}", painterResource(id = R.drawable.headcheck))
    StatsArrayItem(statName = "Acrobatics: ${substats.Acrobatics} ${modifier(substats.Acrobatics)}", painterResource(id = R.drawable.acrobatics))
    StatsArrayItem(statName = "Athletics: ${substats.Athletics} ${modifier(substats.Athletics)}", painterResource(id = R.drawable.run))
    StatsArrayItem(statName = "Deception: ${substats.Deception} ${modifier(substats.Deception)}", painterResource(id = R.drawable.mask))
    StatsArrayItem(statName = "History: ${substats.History} ${modifier(substats.History)}", painterResource(id = R.drawable.script))
    StatsArrayItem(statName = "Insight: ${substats.Insight} ${modifier(substats.Insight)}", painterResource(id = R.drawable.flask))
    StatsArrayItem(statName = "Intimidation: ${substats.Intimidation} ${modifier(substats.Intimidation)}", painterResource(id = R.drawable.angry))
    StatsArrayItem(statName = "Performance: ${substats.Performance} ${modifier(substats.Performance)}", painterResource(id = R.drawable.fawkes))
    StatsArrayItem(statName = "Medicine: ${substats.Medicine} ${modifier(substats.Medicine)}", painterResource(id = R.drawable.medic))
    StatsArrayItem(statName = "Nature: ${substats.Nature} ${modifier(substats.Nature)}", painterResource(id = R.drawable.sprout))
    StatsArrayItem(statName = "Perception: ${substats.Perception} ${modifier(substats.Perception)}", painterResource(id = R.drawable.eye))
    StatsArrayItem(statName = "Persuasion: ${substats.Persuasion} ${modifier(substats.Persuasion)}", painterResource(id = R.drawable.persuasion))
    StatsArrayItem(statName = "Religion: ${substats.Religion} ${modifier(substats.Religion)}", painterResource(id = R.drawable.cross))
    StatsArrayItem(statName = "Stealth: ${substats.Stealth} ${modifier(substats.Stealth)}", painterResource(id = R.drawable.stealth))
    StatsArrayItem(statName = "Survival: ${substats.Survival} ${modifier(substats.Survival)}", painterResource(id = R.drawable.survival))
    StatsArrayItem(statName = "Animal Handling: ${substats.AnimalHandling} ${modifier(substats.AnimalHandling)}", painterResource(id = R.drawable.dog))
}

fun modifier(value: Int): String {
    return when(value) {
        30 -> "(+10)"
        in 28..29 -> "(+9)"
        in 26..27 -> "(+8)"
        in 24..25 -> "(+7)"
        in 22..23 -> "(+6)"
        in 20..21 -> "(+5)"
        in 18..19 -> "(+4)"
        in 16..17 -> "(+3)"
        in 14..15 -> "(+2)"
        in 12..13 -> "(+1)"
        in 10..11 -> "(+0)"
        in 8..9 -> "(-1)"
        in 6..7 -> "(-2)"
        in 4..5 -> "(-3)"
        in 2..3 -> "(-4)"
        1 -> "(-5)"
        else -> "(+0)"
    }
}

// ICONS FOR STATS

// https://materialdesignicons.com/icon/gamepad