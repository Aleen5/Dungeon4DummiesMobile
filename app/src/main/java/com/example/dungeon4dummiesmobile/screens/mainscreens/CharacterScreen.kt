package com.example.dungeon4dummiesmobile.screens.mainscreens

import StatsModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dungeon4dummiesmobile.R
import com.example.dungeon4dummiesmobile.models.CharactersModel
import com.example.dungeon4dummiesmobile.navigation.AppScreens
import com.example.dungeon4dummiesmobile.screens.shared.BottomBar
import com.example.dungeon4dummiesmobile.screens.shared.Drawer
import com.example.dungeon4dummiesmobile.screens.shared.TopBarExtended
import com.example.dungeon4dummiesmobile.screens.shared.TopBarExtendedWithVisibility
import com.example.dungeon4dummiesmobile.ui.theme.MAINCOLOR
import com.example.dungeon4dummiesmobile.ui.theme.SECONDARYCOLOR
import com.example.dungeon4dummiesmobile.viewModels.CharactersViewModel

@Composable
fun CharacterScreen(navController: NavController, username: String, characterID: String) {
    val charactersViewModel = viewModel(CharactersViewModel::class.java)
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val (showDialog, setShowDialog) =  remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var visibility by remember {
        mutableStateOf(true)
    }

    var character by remember {
        mutableStateOf(charactersViewModel.charactersModel)
    }

    var backupCharacter by remember {
        mutableStateOf(charactersViewModel.charactersModel)
    }

    var spoilerCharacter by remember {
        mutableStateOf(charactersViewModel.charactersSpoilerModel)
    }
    spoilerCharacter.alias = backupCharacter.alias
    spoilerCharacter.character_class = backupCharacter.character_class
    spoilerCharacter.archetype = backupCharacter.archetype


    // Spoiler check

    if (visibility)
        character = backupCharacter
    else
        character = spoilerCharacter

    var getData by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = getData) {
        if (getData) {
            getData = false
            charactersViewModel.get1Character(characterID) {
                character = it!!
                backupCharacter = it!!
            }
        }
    }

    Scaffold(
        topBar = { TopBarExtendedWithVisibility(barText = "Character: ${character.alias}", scope = scope, scaffoldState = scaffoldState, visibility = visibility, onVisibilityClick = {visibility = it}) },
        bottomBar = { BottomBar(navController, username) },
        drawerGesturesEnabled = true,
        drawerContent = {
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController, username)
        }
    ) {
        // Tryhard content

        /*
        Spacer(modifier = Modifier.padding(top = 40.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)) {

            // Carta Nombre, Apellido Y Alias

            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            Text(
                                text = "Alias",
                                fontWeight = FontWeight.Bold,
                                color = MAINCOLOR,
                                fontSize = 20.sp
                            )
                        }

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            Text(
                                text = character.alias,
                                color = MAINCOLOR,
                                fontSize = 20.sp,
                                fontStyle = FontStyle.Italic
                            )
                        }

                        Spacer(modifier = Modifier.height(15.dp))

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Column(horizontalAlignment = Alignment.Start) {
                                Text(
                                    text = "Name",
                                    fontWeight = FontWeight.Bold,
                                    color = MAINCOLOR,
                                    fontSize = 20.sp
                                )
                            }

                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "Surname",
                                    fontWeight = FontWeight.Bold,
                                    color = MAINCOLOR,
                                    fontSize = 20.sp
                                )
                            }
                        }

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            Column() {
                                Text(
                                    text = character.name,
                                    color = MAINCOLOR,
                                    fontSize = 20.sp,
                                    fontStyle = FontStyle.Italic
                                )
                            }



                            Column() {
                                Text(
                                    text = character.surname,
                                    color = MAINCOLOR,
                                    fontSize = 20.sp,
                                    fontStyle = FontStyle.Italic
                                )
                            }
                        }
                    }
                }
            }
        }
        */
        // Normal content
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
                    StatsArrayItem(statName = item, 100, painterResource(id = R.drawable.chest))
                }
                item { StatText(statName = "Attacks & Sorceries")}
                items(character.attacks_sorceries) { attack ->
                    StatsArrayItem(statName = attack, 100, painterResource(id = R.drawable.book))
                }
                item { StatText(statName = "Stats")}
                item { SubstatsList(substats = character.stats)}
                item { StatsText(statName = "HP", statValue = "${character.current_hp}/${character.max_hp}") }
                item { StatsText(statName = "MP", statValue = "${character.current_mana}/${character.max_mana}") }
                item { StatText(statName = "Adventure Journal")}
                items(character.adventure_journal) { tale ->
                    StatsArrayItem(statName = tale, 100, painterResource(id = R.drawable.notebook))
                }
                item { StatText(statName = "Features & Traits")}
                items(character.features_traits) { feat ->
                    StatsArrayItem(statName = feat, 100)
                }
                item { StatsNumber(statName = "Death Saves", statValue = character.death_saves) }
                item { StatsText(statName = "Backstory", statValue = character.backstory) }
                item { StatsText(statName = "Ideals", statValue = character.ideals) }
                item { StatsText(statName = "Proficiencies", statValue = character.proficiencies) }
                item { StatsText(statName = "Flaws", statValue = character.flaws) }
                item { StatsText(statName = "Personality Traits", statValue = character.personality_traits) }
                item { StatsText(statName = "Bonds", statValue = character.bonds) }
                item { StatsNumber(statName = "Age", statValue = character.age) }
                item { StatsText(statName = "Languages", statValue = character.languages) }
                item { StatsText(statName = "Avatar (link)", statValue = character.avatar) }

                // Delete Character Button

                item {
                    Button(
                        onClick = {
                            setShowDialog(true)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            Icon(painterResource(id = R.drawable.delete), "",
                                modifier = Modifier
                                    .height(25.dp)
                                    .width(25.dp))
                            Spacer(modifier = Modifier.width(10.dp))
                            Text("Delete Character", fontSize = 18.sp, textAlign = TextAlign.Center)
                        }
                        DeleteDialog(navController, username, showDialog, setShowDialog, character.id)
                    } }
                item { Spacer(modifier = Modifier.height(60.dp))}
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
            color = Color.White
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
            color = Color.White
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
            color = Color.White
        )
    }
}

@Composable
fun StatsArrayItem(statName: String, mod: Int, painter: Painter = painterResource(R.drawable.list)) {
    Row() {
        Spacer(modifier = Modifier.width(5.dp))
        Icon(painter, contentDescription = "", tint = Color.LightGray)
        Spacer(modifier = Modifier.width(3.dp))
        Text(
            text = statName,
            fontStyle = FontStyle.Italic,
            color = Color.White
        )
        Spacer(modifier = Modifier.width(5.dp))

        if (mod == 10 || mod == 11)
            Text(
                text = modifier(mod),
                fontStyle = FontStyle.Italic,
                color = Color.Yellow
            )
        else if (mod >= 12)
            Text(
                text = modifier(mod),
                fontStyle = FontStyle.Italic,
                color = Color.Green
            )
        else
            Text(
                text = modifier(mod),
                fontStyle = FontStyle.Italic,
                color = Color.Red
            )
    }
}

@Composable
fun SubstatsList(substats: StatsModel) {
    StatsArrayItem(statName = "Armor Class: ${substats.ArmorClass}", 100, painterResource(id = R.drawable.shield))
    StatsArrayItem(statName = "Initiative: ${substats.Initiative}", substats.Initiative, painterResource(id = R.drawable.initiative))
    StatsArrayItem(statName = "Strength: ${substats.Strength}", substats.Strength, painterResource(id = R.drawable.strength))
    StatsArrayItem(statName = "Dexterity: ${substats.Dexterity}", substats.Dexterity, painterResource(id = R.drawable.karate))
    StatsArrayItem(statName = "Constitution: ${substats.Constitution}", substats.Constitution, painterResource(id = R.drawable.heart))
    StatsArrayItem(statName = "Intelligence: ${substats.Intelligence}", substats.Intelligence, painterResource(id = R.drawable.idea))
    StatsArrayItem(statName = "Wisdom: ${substats.Wisdom}", substats.Wisdom, painterResource(id = R.drawable.brain))
    StatsArrayItem(statName = "Charisma: ${substats.Charisma}", substats.Charisma, painterResource(id = R.drawable.headcheck))
    StatsArrayItem(statName = "Acrobatics: ${substats.Acrobatics}", substats.Acrobatics, painterResource(id = R.drawable.acrobatics))
    StatsArrayItem(statName = "Athletics: ${substats.Athletics}", substats.Athletics, painterResource(id = R.drawable.run))
    StatsArrayItem(statName = "Deception: ${substats.Deception}", substats.Deception, painterResource(id = R.drawable.mask))
    StatsArrayItem(statName = "History: ${substats.History}", substats.History, painterResource(id = R.drawable.script))
    StatsArrayItem(statName = "Insight: ${substats.Insight}", substats.Insight, painterResource(id = R.drawable.flask))
    StatsArrayItem(statName = "Intimidation: ${substats.Intimidation}", substats.Intimidation, painterResource(id = R.drawable.angry))
    StatsArrayItem(statName = "Performance: ${substats.Performance}", substats.Performance, painterResource(id = R.drawable.fawkes))
    StatsArrayItem(statName = "Medicine: ${substats.Medicine}", substats.Medicine, painterResource(id = R.drawable.medic))
    StatsArrayItem(statName = "Nature: ${substats.Nature}", substats.Nature, painterResource(id = R.drawable.sprout))
    StatsArrayItem(statName = "Perception: ${substats.Perception}", substats.Perception, painterResource(id = R.drawable.eye))
    StatsArrayItem(statName = "Persuasion: ${substats.Persuasion}", substats.Persuasion, painterResource(id = R.drawable.persuasion))
    StatsArrayItem(statName = "Religion: ${substats.Religion}", substats.Religion, painterResource(id = R.drawable.cross))
    StatsArrayItem(statName = "Stealth: ${substats.Stealth}", substats.Stealth, painterResource(id = R.drawable.stealth))
    StatsArrayItem(statName = "Survival: ${substats.Survival}", substats.Survival, painterResource(id = R.drawable.survival))
    StatsArrayItem(statName = "Animal Handling: ${substats.AnimalHandling}", substats.AnimalHandling, painterResource(id = R.drawable.dog))
}

@Composable
fun DeleteDialog(navController: NavController, username: String, showDialog: Boolean, setShowDialog: (Boolean) -> Unit, characterID: String) {
    val charactersViewModel = CharactersViewModel()

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
            },
            title = {
                Text("Delete Character?", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            },
            confirmButton = {
                Button(
                    onClick = {
                        charactersViewModel.deleteCharacter(characterID)
                        setShowDialog(false)
                        navController.navigate(route = AppScreens.CharactersScreen.route + "/$username") {
                            popUpTo(0)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                ) {
                    Icon(painter = painterResource(id = R.drawable.delete), contentDescription = "YES, DELETE")
                    Text("Yes, DELETE")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        // Change the state to close the dialog
                        setShowDialog(false)
                    },
                ) {
                    Text("No, go back")
                }
            },
            text = {
                Text("You are going to delete this character.\nThis action cannot be undone.\n\nProceed?")
            },
        )
    }
}

fun modifier(value: Int): String {
    return when(value) {
        in 30..99 -> "(+10)"
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
        in -99..1 -> "(-5)"
        else -> ""
    }
}

// ICONS FOR STATS

// https://materialdesignicons.com/icon/gamepad