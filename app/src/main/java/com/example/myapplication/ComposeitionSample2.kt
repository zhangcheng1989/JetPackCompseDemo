package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

@Composable
fun ComposeitionSample2() {

    MaterialTheme{
        Column {
            Text(text = "Use MaterialTheme's provided alpha")
            CompositionLocalProvider(LocalContentAlpha.provides(ContentAlpha.medium)) {
                Text(text = "Mediua value provide for LocalContentAlpha")
                Text(text = "This Text also uses the medium value")
            }

            FruitText(3)
        }
    }
}

@Composable
fun FruitText(fruitSize:Int){
    val resource = LocalContext.current.resources
    val fruitText = resource.getQuantityText(R.plurals.fruit_title,fruitSize)
    Text(text = "$fruitSize $fruitText")
}