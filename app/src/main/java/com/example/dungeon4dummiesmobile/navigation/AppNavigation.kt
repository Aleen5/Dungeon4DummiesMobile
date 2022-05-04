package com.example.dungeon4dummiesmobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dungeon4dummiesmobile.screens.mainscreens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = AppScreens.HomeScreen.route + "/{username}", listOf(navArgument(name = "username") {
            type = NavType.StringType
        })) {
            var username = it.arguments?.getString("username")
            requireNotNull(username)
            HomeScreen(navController, username)
        }
        composable(route = AppScreens.RegisterScreen.route) {
            RegisterScreen(navController)
        }

         composable(route = AppScreens.CharactersScreen.route + "/{username}", listOf(navArgument(name = "username") {
             type = NavType.StringType
         })) {
             var username = it.arguments?.getString("username")
             requireNotNull(username)
             CharactersScreen(navController, username)
         }

        composable(route = AppScreens.ProfileScreen.route + "/{username}", listOf(navArgument(name = "username") {
            type = NavType.StringType
        })) {
            var username = it.arguments?.getString("username")
            requireNotNull(username)
            ProfileScreen(navController, username)
        }

        composable(route = AppScreens.SettingsScreen.route + "/{username}", listOf(navArgument(name = "username") {
            type = NavType.StringType
        })) {
            var username = it.arguments?.getString("username")
            requireNotNull(username)
            SettingsScreen(navController, username)
        }

        composable(route = AppScreens.CharacterScreen.route + "/{username}/{characterID}", listOf(
            navArgument(name = "username") {
                type = NavType.StringType },
            navArgument(name = "characterID") {
                type = NavType.StringType
            })) {
            var username = it.arguments?.getString("username")
            var characterID = it.arguments?.getString("characterID")
            requireNotNull(username)
            requireNotNull(characterID)
            CharacterScreen(navController, username, characterID)
        }

        composable(route = AppScreens.CharacterCreationScreen.route + "/{username}", listOf(navArgument(name = "username") {
            type = NavType.StringType
        })) {
            var username = it.arguments?.getString("username")
            requireNotNull(username)
            SettingsScreen(navController, username)
        }
    }
}