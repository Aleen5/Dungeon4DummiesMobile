package com.example.dungeon4dummiesmobile.screens.mainscreens

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.dungeon4dummiesmobile.R
import com.example.dungeon4dummiesmobile.models.CharactersModel
import com.example.dungeon4dummiesmobile.navigation.AppScreens
import com.example.dungeon4dummiesmobile.screens.shared.*
import com.example.dungeon4dummiesmobile.viewModels.CharactersViewModel

@Composable
fun CharactersScreen(navController: NavController, username: String) {
    val charactersViewModel = viewModel(CharactersViewModel::class.java)
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    var visibility by remember {
        mutableStateOf(true)
    }

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
        topBar = { TopBarExtendedWithVisibility(barText = "Characters", scope = scope, scaffoldState = scaffoldState, visibility = visibility, onVisibilityClick = {visibility = it}) },
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
                    CharacterCard(
                        navController = navController,
                        character = character,
                        visibility = visibility,
                    )
                }
                item { CreateCharacterScreenButton(navController = navController, username = username) }
                item { Spacer(modifier = Modifier.height(60.dp)) }
            }
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Nothing to show here. Maybe create a new character?", modifier = Modifier.padding(top = 40.dp))
                CreateCharacterScreenButton(navController = navController, username = username)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterCard(navController: NavController,
                  character: CharactersModel,
                  visibility: Boolean,
                  characterSP: CharactersViewModel = CharactersViewModel()) {

    val spoilerCharacter = characterSP.charactersSpoilerModel
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 18.dp)
            .combinedClickable(
                onClick = {
                    navController.navigate(route = AppScreens.CharacterScreen.route + "/${character.owner}/${character.id}")
                },
                onLongClick = {
                    Toast.makeText(context, "${character.alignment}", Toast.LENGTH_LONG).show()
                }
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,

        ) {
            if (visibility)
                Image(
                    painter = painterResource(id = R.drawable.dungeon4dummieslogotextless),
                    "Character_Avatar",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(7.dp))
            else
                Image(
                    painter = painterResource(id = R.drawable.spoileravatar),
                    "Character_Avatar",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(7.dp)
                        .clip(CircleShape))

            Column {
                Row() { Text("${character.alias}", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 5.dp)) }
                Row() {
                    if (visibility)
                        Text("Lvl ${character.level}")
                    else
                        Text("Lvl ???")
                    Spacer(modifier = Modifier.width(15.dp))
                    ClassIcon(characterClass = character.character_class)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text("${character.character_class} ${character.archetype}")
                }
                Row() {
                    Text(text = "Status: ")
                    if (visibility) {
                        if (character.status.lowercase() == "alive") {
                            Text(
                                "${character.status}",
                                color = Color.Green,
                                fontStyle = FontStyle.Italic
                            )
                        } else if (character.status.lowercase() == "dead") {
                            Text(
                                "${character.status}",
                                color = Color.Red,
                                fontStyle = FontStyle.Italic
                            )
                        } else {
                            Text(
                                "${character.status}",
                                color = Color.Yellow,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    } else
                        Text(
                            "${spoilerCharacter.status}",
                            color = Color.Yellow,
                            fontStyle = FontStyle.Italic
                        )
                }
                Row() {
                    Icon(painterResource(id = R.drawable.heart), "HP", tint = Color.Red)
                    Spacer(modifier = Modifier.width(5.dp))

                    if (visibility) {
                        Text(
                            "HP: ${character.current_hp}/${character.max_hp}",
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(painterResource(id = R.drawable.flask), "MP", tint = Color.Blue)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text("Mana: ${character.current_mana}/${character.max_mana}",
                            modifier = Modifier.padding(bottom = 5.dp))
                    }
                    else {
                        Text(
                            "HP: ???/???",
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(painterResource(id = R.drawable.flask), "MP", tint = Color.Blue)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text("Mana: ???/???",
                            modifier = Modifier.padding(bottom = 5.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun CreateCharacterScreenButton(navController: NavController, username: String) {
    Button(onClick = {
        navController.navigate(route = AppScreens.CharacterCreationScreen.route + "/$username")
    }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(painterResource(id = R.drawable.plus), "", modifier = Modifier
                    .height(25.dp)
                    .width(25.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Text("Create a new character", fontSize = 18.sp, textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
fun ClassIcon(characterClass: String) {
    var painter = painterResource(id = R.drawable.list)

    when(characterClass.toLowerCase()) {
        "warrior" -> painter = painterResource(id = R.drawable.sword)
        "barbarian" -> painter = painterResource(id = R.drawable.axe)
        "bard" -> painter = painterResource(id = R.drawable.guitar)
        "cleric" -> painter = painterResource(id = R.drawable.cross)
        "druid" -> painter = painterResource(id = R.drawable.cat)
        "fighter" -> painter = painterResource(id = R.drawable.karate)
        "monk" -> painter = painterResource(id = R.drawable.temple)
        "paladin" -> painter = painterResource(id = R.drawable.mace)
        "rogue" -> painter = painterResource(id = R.drawable.knife)
        "sorcerer" -> painter = painterResource(id = R.drawable.staff)
        "warlock" -> painter = painterResource(id = R.drawable.crystalball)
        "wizard" -> painter = painterResource(id = R.drawable.wizardhat)
        "artificer" -> painter = painterResource(id = R.drawable.bomb)
        "blood hunter" -> painter = painterResource(id = R.drawable.sword)
        "ranger" -> painter = painterResource(id = R.drawable.bow)
        else -> painterResource(id = R.drawable.list)
    }
    Icon(painter = painter, "", tint = Color.LightGray)
}