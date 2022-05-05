package com.example.dungeon4dummiesmobile.screens.mainscreens

import android.widget.EditText
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dungeon4dummiesmobile.R
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
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    var racesListExpanded by remember { mutableStateOf(false) }
    var racesListSelectedIndex by remember { mutableStateOf(0) }
    var classesListExpanded by remember { mutableStateOf(false) }
    var classesListSelectedIndex by remember { mutableStateOf(0) }
    var alignmentsListExpanded by remember { mutableStateOf(false) }
    var alignmentsListSelectedIndex by remember { mutableStateOf(0) }
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
        "Sorcerer", "Warlock", "Wizard", "Artificer", "Blood Hunter", "Ranger")

    val alignments = listOf("Lawful-Good", "Lawful-Neutral", "Lawful-Evil", "Neutral-Good", "True-Neutral",
        "Neutral-Evil", "Chaotic-Good", "Chaotic-Neutral", "Chaotic-Evil")

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
        mutableStateOf(races[racesListSelectedIndex])
    }
    var characterClass by remember {
        mutableStateOf(races[classesListSelectedIndex])
    }
    var alignment by remember {
        mutableStateOf(alignments[alignmentsListSelectedIndex])
    }
    var level by remember {
        mutableStateOf(1)
    }
    var exp by remember {
        mutableStateOf(0)
    }
    var archetype by remember {
        mutableStateOf("")
    }
    var inventory by remember {
        mutableStateOf(mutableListOf(""))
    }
    var stringInventory by remember {
        mutableStateOf("")
    }
    var armorClass by remember {
        mutableStateOf(0)
    }
    var initiative by remember {
        mutableStateOf(0)
    }
    var strength by remember {
        mutableStateOf(0)
    }
    var dexterity by remember {
        mutableStateOf(0)
    }
    var constitution by remember {
        mutableStateOf(0)
    }
    var intelligence by remember {
        mutableStateOf(0)
    }
    var wisdom by remember {
        mutableStateOf(0)
    }
    var charisma by remember {
        mutableStateOf(0)
    }
    var acrobatics by remember {
        mutableStateOf(0)
    }
    var athletics by remember {
        mutableStateOf(0)
    }
    var deception by remember {
        mutableStateOf(0)
    }
    var history by remember {
        mutableStateOf(0)
    }
    var insight by remember {
        mutableStateOf(0)
    }
    var intimidation by remember {
        mutableStateOf(0)
    }
    var performance by remember {
        mutableStateOf(0)
    }
    var medicine by remember {
        mutableStateOf(0)
    }
    var nature by remember {
        mutableStateOf(0)
    }
    var perception by remember {
        mutableStateOf(0)
    }
    var persuasion by remember {
        mutableStateOf(0)
    }
    var religion by remember {
        mutableStateOf(0)
    }
    var stealth by remember {
        mutableStateOf(0)
    }
    var survival by remember {
        mutableStateOf(0)
    }
    var animalHandling by remember {
        mutableStateOf(0)
    }
    var getData by remember {
        mutableStateOf(true)
    }
    var maxHP by remember {
        mutableStateOf(0)
    }
    var currentHP by remember {
        mutableStateOf(0)
    }
    var maxMP by remember {
        mutableStateOf(0)
    }
    var currentMP by remember {
        mutableStateOf(0)
    }
    var temporalHP by remember {
        mutableStateOf(0)
    }
    var adventureJournal by remember {
        mutableStateOf(mutableListOf(""))
    }
    var featuresTraits by remember {
        mutableStateOf(mutableListOf(""))
    }
    var stringFeaturesTraits by remember {
        mutableStateOf("")
    }
    var deathSaves by remember {
        mutableStateOf(3)
    }
    var backstory by remember {
        mutableStateOf("")
    }
    var ideals by remember {
        mutableStateOf("")
    }
    var proficiencies by remember {
        mutableStateOf("")
    }
    var flaws by remember {
        mutableStateOf("")
    }
    var personalityTraits by remember {
        mutableStateOf("")
    }
    var bonds by remember {
        mutableStateOf("")
    }
    var languages by remember {
        mutableStateOf("")
    }
    var avatar by remember {
        mutableStateOf("")
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
                modifier = Modifier.padding(vertical = 50.dp).fillMaxWidth(),
                backgroundColor = MAINCOLOR

            ) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item { LogoAsset() }
                    item { InputTextField("Name", name, onValueChange = {name = it}) }
                    item { InputTextField("Surname", surname, onValueChange = {surname = it}) }
                    item { InputTextField("Alias", alias, onValueChange = {alias = it}) }
                    item { ComposeMenu(
                            menuItems = races,
                            menuExpandedState = racesListExpanded,
                            seletedIndex = racesListSelectedIndex,
                            updateMenuExpandStatus = {
                                racesListExpanded = true
                            },
                            onDismissMenuView = {
                                racesListExpanded = false
                            },
                            onMenuItemclick = { index->
                                racesListSelectedIndex = index
                                race = races[racesListSelectedIndex]
                                racesListExpanded = false
                            }
                        )
                    }
                    item { ComposeMenu(
                        menuItems = classes,
                        menuExpandedState = classesListExpanded,
                        seletedIndex = classesListSelectedIndex,
                        updateMenuExpandStatus = {
                            classesListExpanded = true
                        },
                        onDismissMenuView = {
                            classesListExpanded = false
                        },
                        onMenuItemclick = { index->
                            classesListSelectedIndex = index
                            characterClass = classes[classesListSelectedIndex]
                            classesListExpanded = false
                        }
                    )
                    }
                    item { ComposeMenu(
                            menuItems = alignments,
                            menuExpandedState = alignmentsListExpanded,
                            seletedIndex = alignmentsListSelectedIndex,
                            updateMenuExpandStatus = {
                                alignmentsListExpanded = true
                            },
                            onDismissMenuView = {
                                alignmentsListExpanded = false
                            },
                            onMenuItemclick = { index->
                                alignmentsListSelectedIndex = index
                                alignment = alignments[alignmentsListSelectedIndex]
                                alignmentsListExpanded = false
                            }
                        )
                    }
                    item { Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            NumericInput(label = "Level", number = level, onValueChange = {level = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            NumericInput(label = "Exp", number = exp, onValueChange = {exp = it})
                        }
                    } }
                    item { InputTextField("Archetype", archetype, onValueChange = {archetype = it}) }
                    item { LongInputTextField(label = "Inventory (separate items with [ ; ])", inValue = stringInventory, onValueChange = {stringInventory = it})}
                    item { Text(text = "Stats", modifier = Modifier.padding(15.dp), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)}
                    item { Row() {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.shield), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Armor Class", number = armorClass, onValueChange = {armorClass = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.initiative), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Initiative", number = initiative, onValueChange = {initiative = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.strength), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Strength", number = strength, onValueChange = {strength = it})
                        }
                    } }
                    item { Row() {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.karate), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Dexterity", number = dexterity, onValueChange = {dexterity = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.heart), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Constitution", number = constitution, onValueChange = {constitution = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.idea), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Intelligence", number = intelligence, onValueChange = {intelligence = it})
                            }
                    } }
                    item { Row() {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.brain), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Wisdom", number = wisdom, onValueChange = {wisdom = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.headcheck), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Charisma", number = charisma, onValueChange = {charisma = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.acrobatics), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Acrobatics", number = acrobatics, onValueChange = {acrobatics = it})
                        }
                    } }
                    item { Row() {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.run), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Athletics", number = athletics, onValueChange = {athletics = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.mask), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Deception", number = deception, onValueChange = {deception = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.script), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "History", number = history, onValueChange = {history = it})
                        }
                    } }
                    item { Row() {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.flask), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Insight", number = insight, onValueChange = {insight = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.angry), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Intimidation", number = intimidation, onValueChange = {intimidation = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.fawkes), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Performance", number = performance, onValueChange = {performance = it})
                        }
                    } }
                    item { Row() {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.medic), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Medicine", number = medicine, onValueChange = {medicine = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.sprout), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Nature", number = nature, onValueChange = {nature = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.eye), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Perception", number = perception, onValueChange = {perception = it})
                        }
                    } }
                    item { Row() {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.persuasion), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Persuasion", number = persuasion, onValueChange = {persuasion = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.cross), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Religion", number = religion, onValueChange = {religion = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.stealth), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Stealth", number = stealth, onValueChange = {stealth = it})
                        }
                    } }
                    item { Row() {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.survival), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Survival", number = survival, onValueChange = {survival = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.dog), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            NumericInput(label = "Animal Handling", number = animalHandling, onValueChange = {animalHandling = it})
                        }
                    } }
                    item { Row() {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.heart), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.Red)
                            NumericInput(label = "HP", number = maxHP, onValueChange = {
                                maxHP = it
                                currentHP = it
                            })
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.flask), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.Blue)
                            NumericInput(label = "MP", number = maxMP, onValueChange = {
                                maxMP = it
                                currentMP = it
                            })
                        }
                    } }

                    item { LongInputTextField(label = "Features & Traits (separate items with [ ; ])", inValue = stringFeaturesTraits, onValueChange = {stringFeaturesTraits = it})}
                    item { LongInputTextField(label = "Backstory", inValue = backstory, onValueChange = {backstory = it})}
                    item { LongInputTextField(label = "Ideals", inValue = ideals, onValueChange = {ideals = it})}
                    item { LongInputTextField(label = "Proficiencies", inValue = proficiencies, onValueChange = {proficiencies = it})}
                    item { LongInputTextField(label = "Flaws", inValue = flaws, onValueChange = {flaws = it})}
                    item { LongInputTextField(label = "Personality Traits", inValue = personalityTraits, onValueChange = {personalityTraits = it})}
                    item { LongInputTextField(label = "Bonds", inValue = bonds, onValueChange = {bonds = it})}
                    item { LongInputTextField(label = "Languages", inValue = languages, onValueChange = {languages = it})}
                    item { LongInputTextField(label = "Avatar (direct link)", inValue = avatar, onValueChange = {avatar = it})}

                    ///// END /////

                    item { Spacer(modifier = Modifier.height(50.dp)) }

                    item { Button(onClick = {
                        inventory = trimStringArray(stringInventory.split(";").toMutableList())
                        featuresTraits = trimStringArray(stringFeaturesTraits.split(";").toMutableList())
                        Toast.makeText(context, "$race $characterClass $alignment $level $inventory ${inventory.size}", Toast.LENGTH_SHORT).show()
                    }) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painter = painterResource(R.drawable.d20), "", tint = Color.Cyan,
                            modifier = Modifier.height(50.dp).width(50.dp))
                            Text(text = "Create Character")
                        }
                    }}
                    item { Spacer(modifier = Modifier.height(50.dp)) }
                }
            }
        }
    }
}


fun trimStringArray(array: MutableList<String>): MutableList<String> {
    return array.map{it.trim()}.toMutableList()
}



