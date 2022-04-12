package com.example.dungeon4dummiesmobile.navigation

sealed class AppScreens(val route: String) {
    object LoginScreen: AppScreens("LoginScreen")
    object HomeScreen: AppScreens("HomeScreen")
}
