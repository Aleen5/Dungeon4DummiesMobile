package com.example.dungeon4dummiesmobile.screens.mainscreens

import StatsModel
import android.util.Log
import androidx.activity.compose.BackHandler
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
import com.example.dungeon4dummiesmobile.screens.shared.*
import com.example.dungeon4dummiesmobile.ui.theme.MAINCOLOR
import com.example.dungeon4dummiesmobile.ui.theme.SECONDARYCOLOR
import com.example.dungeon4dummiesmobile.viewModels.CharactersViewModel

@Composable
fun CharacterScreen(navController: NavController, username: String, characterID: String) {
    val charactersViewModel = viewModel(CharactersViewModel::class.java)
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val (showDialog, setShowDialog) =  remember { mutableStateOf(false) }
    val (showBackDialog, setShowBackDialog) =  remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var visibility by remember {
        mutableStateOf(true)
    }

    var visibilityIconVisible by remember {
        mutableStateOf(true)
    }

    var editing by remember {
        mutableStateOf(false)
    }

    var character by remember {
        mutableStateOf(charactersViewModel.charactersModel)
    }

    var modCharacter by remember {
        mutableStateOf(charactersViewModel.charactersModel)
    }

    val races = listOf("Select your race", "Dragonborn", "Dwarf", "Elf", "Gnome", "Half-Elf", "Halfling", "Half-Orc",
        "Human", "Tiefling", "Orc of Exandria", "Leonin", "Satyr", "Fairy", "Harengon", "Owlin",
        "Aarakocra", "Genasi", "Goliath", "Aasimar", "Bugbear", "Firbolg", "Goblin", "Hobgoblin",
        "Kenku", "Kobold", "Lizardfolk", "Orc", "Tabaxi", "Triton", "Yuan-ti Pureblood",
        "Feral Tiefling", "Tortle", "Changeling", "Kalashtar", "Orc of Eberron", "Shifter",
        "Warforged", "Gith", "Centaur", "Loxodon", "Minotaur", "Simic Hybrid", "Vedalken", "Verdan",
        "Locathah", "Grung")

    val classes = listOf("Select your class", "Barbarian", "Bard", "Cleric", "Druid", "Fighter", "Monk", "Paladin", "Rogue",
        "Sorcerer", "Warlock", "Wizard", "Artificer", "Blood Hunter", "Ranger")

    val alignments = listOf("Select your alignment", "Lawful-Good", "Lawful-Neutral", "Lawful-Evil", "Neutral-Good", "True-Neutral",
        "Neutral-Evil", "Chaotic-Good", "Chaotic-Neutral", "Chaotic-Evil")

    var racesListExpanded by remember { mutableStateOf(false) }
    var racesListSelectedIndex by remember { mutableStateOf(0) }

    var classesListExpanded by remember { mutableStateOf(false) }
    var classesListSelectedIndex by remember { mutableStateOf(0) }

    var alignmentsListExpanded by remember { mutableStateOf(false) }
    var alignmentsListSelectedIndex by remember { mutableStateOf(0) }

    var race by remember {
        mutableStateOf(races[racesListSelectedIndex])
    }
    var characterClass by remember {
        mutableStateOf(classes[classesListSelectedIndex])
    }
    var alignment by remember {
        mutableStateOf(alignments[alignmentsListSelectedIndex])
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

    // MOD variables

    var name by remember {
        mutableStateOf("")
    }
    var surname by remember {
        mutableStateOf("")
    }
    var alias by remember {
        mutableStateOf("")
    }
    var status by remember {
        mutableStateOf("Alive")
    }
    var level by remember {
        mutableStateOf(1)
    }
    var exp by remember {
        mutableStateOf(0)
    }
    var age by remember {
        mutableStateOf(20)
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

    var attacksSorceries by remember {
        mutableStateOf(mutableListOf(""))
    }
    var stringAttacksSorceries by remember {
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
    var stringAdventureJournal by remember {
        mutableStateOf("")
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
            charactersViewModel.get1Character(characterID) {
                character = it!!
                backupCharacter = it!!
                name = it.name
                surname = it.surname
                alias = it.alias
                status = it.status
                race = it.race
                alignment = it.alignment
                currentHP = it.current_hp
                maxHP = it.max_hp
                currentMP = it.current_mana
                maxMP = it.max_mana
                level = it.level
                exp = it.exp
                characterClass = it.character_class
                archetype = it.archetype

                inventory = it.inventory
                inventory.forEach { str ->
                    stringInventory += "$str;\n"
                }
                stringInventory.trim()
                stringInventory.removeSuffix(";")

                attacksSorceries = it.attacks_sorceries
                attacksSorceries.forEach { str ->
                    stringAttacksSorceries += "$str;\n"
                }
                stringAttacksSorceries.trim()
                stringAttacksSorceries.removeSuffix(";")

                armorClass = it.stats.ArmorClass
                initiative = it.stats.Initiative
                strength = it.stats.Strength
                dexterity = it.stats.Dexterity
                constitution = it.stats.Constitution
                intelligence = it.stats.Intelligence
                wisdom = it.stats.Wisdom
                charisma = it.stats.Charisma
                acrobatics = it.stats.Acrobatics
                athletics = it.stats.Athletics
                deception = it.stats.Deception
                history = it.stats.History
                insight = it.stats.Insight
                intimidation = it.stats.Intimidation
                performance = it.stats.Performance
                medicine = it.stats.Medicine
                nature = it.stats.Nature
                perception = it.stats.Perception
                persuasion = it.stats.Persuasion
                religion = it.stats.Religion
                stealth = it.stats.Stealth
                survival = it.stats.Survival
                animalHandling = it.stats.AnimalHandling

                adventureJournal = it.adventure_journal
                adventureJournal.forEach { str ->
                    stringAdventureJournal += "$str;\n"
                }
                stringAdventureJournal.trim()
                stringAdventureJournal.removeSuffix(";")

                featuresTraits = it.features_traits
                featuresTraits.forEach { str ->
                    stringFeaturesTraits += "$str;\n"
                }
                stringFeaturesTraits.trim()
                stringFeaturesTraits.removeSuffix(";")

                deathSaves = it.death_saves
                backstory = it.backstory
                ideals = it.ideals
                proficiencies = it.proficiencies
                flaws = it.flaws
                personalityTraits = it.personality_traits
                bonds = it.bonds
                age = it.age
                languages = it.languages
                avatar = it.avatar
            }
        }
    }

    Scaffold(
        topBar = { TopBarExtendedWithVisibility(
            barText = "Character: ${character.alias}",
            scope = scope, scaffoldState = scaffoldState,
            visibility = visibility,
            visibilityIconVisible = visibilityIconVisible,
            onVisibilityClick = {visibility = it}) },
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

                // IF EDITING, muestra Inputs para editar.
                // IF !EDITING, muestra textos no editables.

                if (editing) {
                    item {
                        StatText("Name")
                        InputTextField(label = "Name", inValue = name, onValueChange = {name = it})
                    }

                    item {
                        StatText("Surname")
                        InputTextField(label = "Surname", inValue = surname, onValueChange = {surname = it})
                    }
                    item {
                        StatText("Alias")
                        InputTextField(label = "Alias", inValue = alias, onValueChange = {alias = it})
                    }
                    item {
                        StatText("Status")
                        InputTextField(label = "Status", inValue = status, onValueChange = {status = it})
                    }

                    item {
                        StatText("Race")
                        ComposeMenu(
                            menuItems = races,
                            menuExpandedState = racesListExpanded,
                            selectedIndex = racesListSelectedIndex,
                            updateMenuExpandStatus = {
                                racesListExpanded = true
                            },
                            onDismissMenuView = {
                                racesListExpanded = false
                            },
                            onMenuItemClick = { index->
                                racesListSelectedIndex = index
                                race = races[racesListSelectedIndex]
                                racesListExpanded = false
                            }
                        )
                    }

                    item {
                        StatText("Alignment")
                        ComposeMenu(
                            menuItems = alignments,
                            menuExpandedState = alignmentsListExpanded,
                            selectedIndex = alignmentsListSelectedIndex,
                            updateMenuExpandStatus = {
                                alignmentsListExpanded = true
                            },
                            onDismissMenuView = {
                                alignmentsListExpanded = false
                            },
                            onMenuItemClick = { index->
                                alignmentsListSelectedIndex = index
                                alignment = alignments[alignmentsListSelectedIndex]
                                alignmentsListExpanded = false
                            }
                        )
                    }

                    item {
                        StatText("HP")
                        NumericInput(label = "Current HP", number = currentHP, onValueChange = {currentHP = it})
                        StatText("Temporal HP")
                        NumericInput(label = "Temporal HP", number = temporalHP, onValueChange = {temporalHP = it})
                        StatText("Max HP")
                        NumericInput(label = "Maximum HP", number = maxHP, onValueChange = {maxHP = it})
                    }
                    item {
                        StatText("MP")
                        NumericInput(label = "Current MP", number = currentMP, onValueChange = {currentMP = it})
                        StatText("Max MP")
                        NumericInput(label = "Maximum MP", number = currentMP, onValueChange = {currentMP = it})
                    }
                    item {
                        StatText("Character Level")
                        NumericInput(label = "Level", number = level, onValueChange = {level = it})
                    }

                    item {
                        StatText("Experience Points")
                        NumericInput(label = "Exp", number = exp, onValueChange = {exp = it})
                    }

                    item {
                        StatText("Class")
                        ComposeMenu(
                            menuItems = classes,
                            menuExpandedState = classesListExpanded,
                            selectedIndex = classesListSelectedIndex,
                            updateMenuExpandStatus = {
                                classesListExpanded = true
                            },
                            onDismissMenuView = {
                                classesListExpanded = false
                            },
                            onMenuItemClick = { index->
                                classesListSelectedIndex = index
                                characterClass = classes[classesListSelectedIndex]
                                classesListExpanded = false
                            }
                        )
                    }

                    item {
                        StatText("Archetype")
                        InputTextField(label = "Archetype", inValue = archetype, onValueChange = {archetype = it})
                    }
                    item {
                        StatText("Inventory")
                        LongInputTextField(label = "Inventory (separate items with [ ; ])", inValue = stringInventory, onValueChange = {stringInventory = it})
                    }
                    item {
                        StatText("Attacks & Sorceries")
                        LongInputTextField(label = "Attacks & Sorceries (separate items with [ ; ])", inValue = stringAttacksSorceries, onValueChange = {stringAttacksSorceries = it})
                    }

                    item { Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.shield), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            ReducedNumericInput(label = "Armor Class", number = armorClass, onValueChange = {armorClass = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.initiative), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            ReducedNumericInput(label = "Initiative", number = initiative, onValueChange = {initiative = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.strength), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            ReducedNumericInput(label = "Strength", number = strength, onValueChange = {strength = it})
                        }
                    } }
                    item { Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.karate), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            ReducedNumericInput(label = "Dexterity", number = dexterity, onValueChange = {dexterity = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.heart), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            ReducedNumericInput(label = "Constitution", number = constitution, onValueChange = {constitution = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.idea), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            ReducedNumericInput(label = "Intelligence", number = intelligence, onValueChange = {intelligence = it})
                        }
                    } }
                    item { Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.brain), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            ReducedNumericInput(label = "Wisdom", number = wisdom, onValueChange = {wisdom = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.headcheck), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            ReducedNumericInput(label = "Charisma", number = charisma, onValueChange = {charisma = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.acrobatics), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            ReducedNumericInput(label = "Acrobatics", number = acrobatics, onValueChange = {acrobatics = it})
                        }
                    } }
                    item { Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.run), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            ReducedNumericInput(label = "Athletics", number = athletics, onValueChange = {athletics = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.mask), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            ReducedNumericInput(label = "Deception", number = deception, onValueChange = {deception = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.script), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            ReducedNumericInput(label = "History", number = history, onValueChange = {history = it})
                        }
                    } }
                    item { Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.flask), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            ReducedNumericInput(label = "Insight", number = insight, onValueChange = {insight = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.angry), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            ReducedNumericInput(label = "Intimidation", number = intimidation, onValueChange = {intimidation = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.fawkes), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            ReducedNumericInput(label = "Performance", number = performance, onValueChange = {performance = it})
                        }
                    } }
                    item { Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.medic), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            ReducedNumericInput(label = "Medicine", number = medicine, onValueChange = {medicine = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.sprout), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            ReducedNumericInput(label = "Nature", number = nature, onValueChange = {nature = it})
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painterResource(R.drawable.eye), "", modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), tint = Color.LightGray)
                            ReducedNumericInput(label = "Perception", number = perception, onValueChange = {perception = it})
                        }
                    } }

                    item {
                        StatText("Adventure Journal")
                        LongInputTextField(label = "Adventure Journal (separate stories with [ ; ])", inValue = stringAdventureJournal, onValueChange = {stringAdventureJournal = it})
                    }

                    item {
                        StatText("Features & Traits")
                        LongInputTextField(label = "Features & Traits (separate things with [ ; ])", inValue = stringFeaturesTraits, onValueChange = {stringFeaturesTraits = it})
                    }

                    item {
                        StatText("Death Saves")
                        NumericInput(label = "Death Saves", number = deathSaves, onValueChange = {deathSaves = it})
                    }

                    item {
                        StatText("Backstory")
                        LongInputTextField(label = "Tell us your story", inValue = backstory, onValueChange = {backstory = it})
                    }

                    item {
                        StatText("Ideals")
                        LongInputTextField(label = "What are your ideals?", inValue = ideals, onValueChange = {ideals = it})
                    }

                    item {
                        StatText("Proficiencies")
                        LongInputTextField(label = "What are you good at?", inValue = proficiencies, onValueChange = {proficiencies = it})
                    }

                    item {
                        StatText("Flaws")
                        LongInputTextField(label = "What are you bad at?", inValue = flaws, onValueChange = {flaws = it})
                    }

                    item {
                        StatText("Personality Traits")
                        LongInputTextField(label = "What makes your personality so singular?", inValue = personalityTraits, onValueChange = {personalityTraits = it})
                    }

                    item {
                        StatText("Bonds")
                        LongInputTextField(label = "With whom do you get along well?", inValue = bonds, onValueChange = {bonds = it})
                    }

                    item {
                        StatText("Age")
                        NumericInput(label = "How old are you?", number = age, onValueChange = {age = it})
                    }

                    item {
                        StatText("Languages")
                        InputTextField(label = "What languages do you speak?", inValue = languages, onValueChange = {languages = it})
                    }

                    item {
                        StatText("Avatar")
                        InputTextField(label = "What do you look like? (insert a direct link to image)", inValue = avatar, onValueChange = {avatar = it})
                    }

                } else {
                    item { StatsText(statName = "Name", statValue = character.name) }
                    item { StatsText(statName = "Surname", statValue = character.surname) }
                    item { StatsText(statName = "Alias", statValue = character.alias) }
                    item { StatsText(statName = "Status", statValue = character.status) }
                    item { StatsText(statName = "Race", statValue = character.race) }
                    item { StatsText(statName = "Alignment", statValue = character.alignment) }
                    item { StatsText(statName = "HP", statValue = "${character.current_hp}/${character.max_hp}") }
                    item { StatsText(statName = "MP", statValue = "${character.current_mana}/${character.max_mana}") }
                    item { StatsNumber(statName = "Level", statValue = character.level) }
                    item { StatsNumber(statName = "Exp", statValue = character.exp) }
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
                }

                // Edit Character Button

                item {
                    if (!editing && visibility) {
                        Button(
                            onClick = {
                                editing = true
                                visibilityIconVisible = false
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = MAINCOLOR)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                                Icon(painterResource(id = R.drawable.pencil), "",
                                    modifier = Modifier
                                        .height(25.dp)
                                        .width(25.dp))
                                Spacer(modifier = Modifier.width(10.dp))
                                Text("Edit Character", fontSize = 18.sp, textAlign = TextAlign.Center)
                            }
                        }
                    } else if (editing && visibility) {

                        // Save Changes Button

                        Button(
                            onClick = {
                                editing = false
                                visibilityIconVisible = true
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                                Icon(painterResource(id = R.drawable.pencil), "",
                                    modifier = Modifier
                                        .height(25.dp)
                                        .width(25.dp))
                                Spacer(modifier = Modifier.width(10.dp))
                                Text("Save Changes", fontSize = 18.sp, textAlign = TextAlign.Center)
                            }
                        }
                    }
                }

                // Stop Editing Button

                item {  }

                item { Spacer(modifier = Modifier.height(10.dp)) }

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
                    }
                }
                item { Spacer(modifier = Modifier.height(60.dp))}


            }

            // Handle On Back Pressed while editing a character

            if (editing) {
                EditScreenBackHandler(backShowDialog = showBackDialog,
                    backSetShowDialog = setShowBackDialog)
                BackDialog(
                    navController = navController,
                    username = username,
                    showDialog = showBackDialog,
                    setShowDialog = setShowBackDialog
                )
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

@Composable
fun EditScreenBackHandler(backShowDialog: Boolean, backSetShowDialog: (Boolean) -> Unit) {
    BackHandler {
        backSetShowDialog(true)
    }
}

@Composable
fun BackDialog(navController: NavController, username: String, showDialog: Boolean, setShowDialog: (Boolean) -> Unit) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
            },
            title = {
                Text("Discard Changes?", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            },
            confirmButton = {
                Button(
                    onClick = {
                        setShowDialog(false)
                        navController.navigate(route = AppScreens.CharactersScreen.route + "/$username") {
                            popUpTo(0)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                ) {
                    Icon(painter = painterResource(id = R.drawable.delete), contentDescription = "Go back")
                    Text("Yes, undo changes")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        // Change the state to close the dialog
                        setShowDialog(false)
                    },
                ) {
                    Text("No, wait")
                }
            },
            text = {
                Text("Changes will be undone. \n\nProceed?")
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