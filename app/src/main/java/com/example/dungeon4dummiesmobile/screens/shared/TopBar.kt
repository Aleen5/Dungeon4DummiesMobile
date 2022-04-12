package com.example.dungeon4dummiesmobile.screens.shared

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dungeon4dummiesmobile.R

@Composable
fun TopBar(barText: String) {
    TopAppBar(
        backgroundColor = colorResource(id = R.color.MAINCOLOR)

    ) {
        Text(
            modifier = Modifier.padding(start = 15.dp),
            text = barText,
            color = Color.Black,
            fontSize = 25.sp,

            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold,
        )
    }
}