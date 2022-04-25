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
            HomeScreen(navController, it.arguments?.getString("username"))
        }
        composable(route = AppScreens.RegisterScreen.route) {
            RegisterScreen(navController)
        }

         composable(route = AppScreens.CharactersScreen.route) {
             CharactersScreen(navController)
         }

        composable(route = AppScreens.CharactersScreen.route) {
            CharactersScreen(navController)
        }

        composable(route = AppScreens.ProfileScreen.route) {
            ProfileScreen(navController)
        }

        composable(route = AppScreens.SettingsScreen.route) {
            SettingsScreen(navController)
        }
    }
}