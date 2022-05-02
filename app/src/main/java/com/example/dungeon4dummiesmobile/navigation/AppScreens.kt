package com.example.dungeon4dummiesmobile.navigation

sealed class AppScreens(val route: String) {
    object LoginScreen: AppScreens("LoginScreen")
    object HomeScreen: AppScreens("HomeScreen")
    object RegisterScreen: AppScreens("RegisterScreen")
    object CharactersScreen: AppScreens("CharactersScreen")
    object CharacterScreen: AppScreens("CharacterScreen")
    object ProfileScreen: AppScreens("ProfileScreen")
    object SettingsScreen: AppScreens("SettingsScreen")
}
