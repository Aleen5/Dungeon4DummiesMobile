package com.example.dungeon4dummiesmobile.screens.mainscreens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController, username: String?) {
    username?.let { Text(text = it) }
}