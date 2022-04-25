package com.example.dungeon4dummiesmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.dungeon4dummiesmobile.navigation.AppNavigation
import com.example.dungeon4dummiesmobile.ui.theme.Dungeon4DummiesMobileTheme
import com.example.dungeon4dummiesmobile.viewModels.UsersViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Dungeon4DummiesMobileTheme {
                AppNavigation()
            }
        }
    }
}