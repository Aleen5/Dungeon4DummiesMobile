package com.example.dungeon4dummiesmobile.screens.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.dungeon4dummiesmobile.R

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
fun LongInputTextField(label: String, inValue: String, onValueChange:(textUser: String) -> Unit) {
    TextField(
        value = inValue,
        onValueChange = { onValueChange(it) },
        label = { Text(label) },
        modifier = Modifier.padding(16.dp).height(120.dp)
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
                Icon(painter = image, contentDescription = description, tint = Color.LightGray)
            }
        }
    )
}

@Composable
fun NumericInput(label: String, number: Int, onValueChange:(intValue: Int) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row() {
            TextField(
                value = number.toString(),
                onValueChange = {
                    onValueChange(IntegerTryParse(it))
                },
                label = { Text(label, textAlign = TextAlign.Center, fontSize = 10.sp) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.padding(16.dp).width(100.dp).height(60.dp)
            )
        }
    }
}

@Composable
fun ComposeMenu(
    menuItems: List<String>,
    menuExpandedState: Boolean,
    seletedIndex: Int,
    updateMenuExpandStatus : () -> Unit,
    onDismissMenuView : () -> Unit,
    onMenuItemclick: (Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
            .padding(top = 16.dp, bottom = 16.dp, start = 55.dp, end = 55.dp)
            .border(0.5.dp, MaterialTheme.colors.onSurface.copy(alpha = 0.5f))
            .clickable(
                onClick = {
                    updateMenuExpandStatus()
                },
            ),

        ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            val (lable, iconView) = createRefs()

            Text(
                text = menuItems[seletedIndex],
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(lable) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(iconView.start)
                        width = Dimension.fillToConstraints
                    }
            )

            val displayIcon: Painter = painterResource(
                id = R.drawable.menudown
            )

            Icon(
                painter = displayIcon,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp, 20.dp)
                    .constrainAs(iconView) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                tint = MaterialTheme.colors.onSurface
            )

            DropdownMenu(
                expanded = menuExpandedState,
                onDismissRequest = { onDismissMenuView() },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
            ) {
                menuItems.forEachIndexed { index, title ->
                    DropdownMenuItem(
                        onClick = {
                            if (index != 0) {
                                onMenuItemclick(index)
                            }
                        }) {
                        Text(text = title)
                    }
                }
            }
        }
    }
}

fun IntegerTryParse(str: String): Int {
    return try {
        val parsedInt = str.toInt()
        parsedInt
    } catch (nfe: NumberFormatException) {
        0
    }
}