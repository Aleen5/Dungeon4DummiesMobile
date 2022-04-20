package com.example.dungeon4dummiesmobile.screens.shared

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.dungeon4dummiesmobile.ui.theme.Dungeon4DummiesMobileTheme

@Composable
fun DialogLoading(loading : MutableState<Boolean>) {
    Dungeon4DummiesMobileTheme() {
        if (loading.value) {
            Dialog(onDismissRequest = { /*TODO */ }) {
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(color = Color(0xfffcffff), modifier = Modifier
                        .height(30.dp)
                        .width(30.dp))
                    Text(text = "Loading...", fontSize = 16.sp, color = Color(0xfffcffff))
                }
            }
        }
    }
}