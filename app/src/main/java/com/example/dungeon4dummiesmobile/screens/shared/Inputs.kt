package com.example.dungeon4dummiesmobile.screens.shared

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.dungeon4dummiesmobile.R
import com.example.dungeon4dummiesmobile.screens.mainscreens.modifier

@Composable
fun InputTextField(label: String, inValue: String, onValueChange:(textUser: String) -> Unit) {
    TextField(
        value = inValue,
        onValueChange = { onValueChange(it) },
        label = { Text(label) },
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun PasswordTextField(label: String, password: String, onValueChange:(textPassword: String) -> Unit) {
    var passwordVisible by rememberSaveable{ mutableStateOf(false) }

    TextField(
        value = password,
        onValueChange = { onValueChange(it) },
        label = { Text(label) },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier.padding(16.dp),
        trailingIcon = {
            val image = if (passwordVisible)
                painterResource(id = R.drawable.eyeoff)
            else painterResource(id = R.drawable.eye)

            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(painter = image, contentDescription = description)
            }
        }
    )
}