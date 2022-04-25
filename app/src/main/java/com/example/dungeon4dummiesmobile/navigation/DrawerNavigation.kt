package com.example.dungeon4dummiesmobile.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DrawerNavigation(var route: String, var icon: ImageVector, var title: String) {
    object Profile: DrawerNavigation(AppScreens.ProfileScreen.route, Icons.Filled.AccountBox, "Profile")
    object Settings: DrawerNavigation(AppScreens.SettingsScreen.route, Icons.Filled.Settings, "Settings")
}
