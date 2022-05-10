package com.example.dungeon4dummiesmobile.screens.mainscreens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dungeon4dummiesmobile.R
import com.example.dungeon4dummiesmobile.screens.shared.*
import com.example.dungeon4dummiesmobile.viewModels.CharactersViewModel
import com.example.dungeon4dummiesmobile.viewModels.UsersViewModel

@Composable
fun DiceThrowScreen(navController: NavController, username: String) {
    val usersViewModel = viewModel(UsersViewModel::class.java)
    val charactersViewModel = viewModel(CharactersViewModel::class.java)
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var charactersListExpanded by remember { mutableStateOf(false) }
    var charactersListSelectedIndex by remember { mutableStateOf(0) }
    var selectedCharacter by remember {
        mutableStateOf(charactersViewModel.charactersModel)
    }
    val context = LocalContext.current

    var user by remember {
        mutableStateOf(usersViewModel.user)
    }

    var charactersList by remember {
        mutableStateOf(charactersViewModel.charactersModelListResponse)
    }

    if (charactersList.size < 1) {
        var firstCharacter = charactersViewModel.charactersModel
        firstCharacter.alias = "Select your character"

        charactersList.add(0, firstCharacter)
    }

    var amount by remember {
        mutableStateOf(0)
    }

    var modifier by remember {
        mutableStateOf(0)
    }

    var totalAmount by remember {
        mutableStateOf(0)
    }

    var getData by remember {
        mutableStateOf(true)
    }

    var hp by remember {
        mutableStateOf(0)
    }

    var maxHP by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(key1 = getData) {
        if (getData) {

            getData = false
            usersViewModel.get1User(username) {
                user = usersViewModel.user
            }
            charactersViewModel.getUsersCharacters(username) { list ->
                //charactersList = it.toMutableList()
                list.forEach {
                    charactersList.add(it)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopBarExtended(
                barText = "Dice Throw",
                scope = scope,
                scaffoldState = scaffoldState
            )
        },
        bottomBar = { BottomBar(navController, username) },
        drawerGesturesEnabled = true,
        drawerContent = {
            Drawer(
                scope = scope,
                scaffoldState = scaffoldState,
                navController = navController,
                username
            )
        },
        scaffoldState = scaffoldState
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(top = 10.dp, end = 10.dp, start = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Let's throw a die",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        fontFamily = FontFamily.Cursive
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
            }

            // Characters List

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ComposeMenu(
                        menuItems = charactersList.map { it.alias },
                        menuExpandedState = charactersListExpanded,
                        selectedIndex = charactersListSelectedIndex,
                        updateMenuExpandStatus = {
                            charactersListExpanded = true
                        },
                        onDismissMenuView = {
                            charactersListExpanded = false
                        },
                        onMenuItemClick = { index ->
                            charactersListSelectedIndex = index
                            selectedCharacter = charactersList[charactersListSelectedIndex]
                            charactersListExpanded = false
                            hp = selectedCharacter.current_hp
                            maxHP = selectedCharacter.max_hp
                        }
                    )
                }

                Spacer(modifier = Modifier.width(5.dp))

                Icon(painter = painterResource(id = R.drawable.shield), "Armor Class",
                modifier = Modifier.size(35.dp))
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "${selectedCharacter.stats.ArmorClass}", fontSize = 20.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(2.dp))
            }

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.heart),
                        "Heart icon",
                        tint = Color.Red,
                        modifier = Modifier.size(35.dp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "$hp/${selectedCharacter.max_hp}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(15.dp))
            }

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            amount = (1..10).random() * 10
                            totalAmount = amount + modifier
                        },
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.d100
                            ),
                            contentDescription = "100Die",
                            modifier = Modifier.size(60.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = {
                            amount = (1..20).random()
                            totalAmount = amount + modifier
                        },
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.d20
                            ),
                            contentDescription = "20Die",
                            modifier = Modifier.size(60.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = {
                            amount = (1..12).random()
                            totalAmount = amount + modifier
                        },
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.d12
                            ),
                            contentDescription = "12Die",
                            modifier = Modifier.size(60.dp)
                        )
                    }
                }


                Spacer(modifier = Modifier.height(10.dp))

            }

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            amount = (1..10).random()
                            totalAmount = amount + modifier
                        },
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.d10
                            ),
                            contentDescription = "10Die",
                            modifier = Modifier.size(60.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = {
                            amount = (1..8).random()
                            totalAmount = amount + modifier
                        },
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.d8
                            ),
                            contentDescription = "8Die",
                            modifier = Modifier.size(60.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = {
                            amount = (1..6).random()
                            totalAmount = amount + modifier
                        },
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.d6
                            ),
                            contentDescription = "6Die",
                            modifier = Modifier.size(60.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

            }

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            if (hp - totalAmount < 1)
                                hp = 0
                            else
                                hp -= totalAmount
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Text(text = "Deal", fontWeight = FontWeight.Bold)
                        Icon(painter = painterResource(id = R.drawable.sword), "Damage Sword", modifier = Modifier.size(35.dp))
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = {
                            amount = (1..4).random()
                            totalAmount = amount + modifier
                        },
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.d4
                            ),
                            contentDescription = "4Die",
                            modifier = Modifier.size(60.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = {
                            if (hp + totalAmount > maxHP)
                                hp = maxHP
                            else
                                hp += totalAmount
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(painter = painterResource(id = R.drawable.heal), "Heal Cross", modifier = Modifier.size(35.dp))
                        Text(text = "Heal", fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))

            }

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    DisabledReducedNumericInput(
                        label = "Roll",
                        number = amount,
                        onValueChange = { amount = it })
                    ReducedNumericInput(
                        label = "Modifier: (+)",
                        number = modifier,
                        onValueChange = {
                            modifier = it
                            totalAmount = amount + it
                        })
                    DisabledReducedNumericInput(
                        label = "Total Amount",
                        number = totalAmount,
                        onValueChange = { totalAmount = it })
                }


                Spacer(modifier = Modifier.height(10.dp))

            }

            item {
                Button(
                    onClick = {

                        if(charactersListSelectedIndex == 0) {
                            Toast.makeText(context, "What are you trying to save?", Toast.LENGTH_LONG).show()
                            return@Button
                        }

                        selectedCharacter.current_hp = hp
                        charactersViewModel.updateCharacter(selectedCharacter)
                        Toast.makeText(context, "Character ${selectedCharacter.alias} updated successfully", Toast.LENGTH_LONG).show()

                    }
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Text(text = "Save changes", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(85.dp)) }
        }
    }
}