package com.example.dungeon4dummiesmobile.screens.shared

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.dungeon4dummiesmobile.R

@Composable
fun TopBar(barText: String) {
    TopAppBar(
        backgroundColor = colorResource(id = R.color.DDARKBROWN)
    ) {
        Text(
            text = barText,
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}