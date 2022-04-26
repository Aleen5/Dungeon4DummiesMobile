package com.example.dungeon4dummiesmobile.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarNavigation(var route: String, var icon: ImageVector, var title: String) {
    object Home : BottomBarNavigation(AppScreens.HomeScreen.route + "/{username}", Icons.Filled.Home, "Home")
    object Characters: BottomBarNavigation(AppScreens.CharactersScreen.route, Icons.Filled.Person, "Characters")
}