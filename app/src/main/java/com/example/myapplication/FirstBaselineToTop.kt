package com.example.myapplication

import androidx.compose.foundation.background

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

fun Modifier.firstBaselineToTop(
    firstBaselineToTop:Dp
)=this.then(
    layout{measurable,constraints ->
        val placeable  = measurable.measure(constraints)
        val firstBaseline = placeable[FirstBaseline]
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        val height = placeable.height + placeableY
        layout(placeable.width,height){
            placeable.placeRelative(0,placeableY)
        }
    }
)

@Composable
fun TextWithPaddingToBaseline(){
    MyApplicationTheme {
        Text(text = "Hi there!",
            Modifier
                .firstBaselineToTop(24.dp)
                .background(Color.Red))
    }
}