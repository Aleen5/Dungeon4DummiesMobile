package com.example.dungeon4dummiesmobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dungeon4dummiesmobile.screens.login.HomeScreen
import com.example.dungeon4dummiesmobile.screens.login.LoginScreen

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
    }
}