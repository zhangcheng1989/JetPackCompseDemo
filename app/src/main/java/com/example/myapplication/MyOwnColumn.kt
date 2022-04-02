package com.example.myapplication

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    content:@Composable () -> Unit
) {

    Layout(modifier = modifier, content = content){ measurables,constraints ->
        val placeables = measurables.map {measurable ->
            measurable.measure(constraints)
        }

        var yPosition = 0

        layout(constraints.maxWidth,constraints.maxHeight){
            placeables.forEach { placeable ->
                placeable.placeRelative(0,yPosition)
                yPosition += placeable.height
            }
        }
    }
}

@Composable
fun MyOwnColumnSample(){
    MyApplicationTheme {
        MyOwnColumn(Modifier.padding(8.dp)) {
            Text(text = "MyOwnColumn")
            Text(text = "places items")
            Text(text = "vertically.")
            Text(text = "We've done it by hand")
        }
    }
}