package com.example.dungeon4dummiesmobile.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarNavigation(var route: String, var icon: ImageVector, var title: String) {
    object Home : BottomBarNavigation(AppScreens.HomeScreen.route, Icons.Filled.Home, "Home")
    object Characters: BottomBarNavigation(AppScreens.CharactersScreen.route, Icons.Filled.Person, "Characters")
    object DiceThrow: BottomBarNavigation(AppScreens.DiceThrowScreen.route, Icons.Filled.PlayArrow, "DiceThrow")
}
