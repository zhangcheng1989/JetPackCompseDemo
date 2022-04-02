package com.example.myapplication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max


val topics = listOf<String>("aaa","bbbb","cccc")

@Composable
fun StaggeredGrid(modifier: Modifier = Modifier,rows:Int = 3,content:@Composable () -> Unit ){
    Layout(modifier = modifier,
    content =  content){measureables,constraints ->
        //用于保存行的宽度值
        val rowWidths = IntArray(rows) {0}
        val rowHeights = IntArray(rows){0}
        val placeables = measureables.mapIndexed{index,measurable ->
            val placeable = measurable.measure(constraints = constraints)
            val row = index % rows
            rowWidths[row] += placeable.width
            rowHeights[row] = max(rowHeights[row],placeable.height)
            placeable
        }

        val width = rowWidths.maxOrNull() ?: constraints.minWidth
        val height = rowHeights.sumOf { it }
        val rowY = IntArray(rows){ 0 }

        for (i in 1 until rows){
            rowY[i] = rowY[i - 1] +rowHeights[i - 1]
        }

        layout(width,height){
            val rowX = IntArray(rows){ 0 }
            placeables.forEachIndexed{ index,placeable ->
                val row = index % rows
                placeable.placeRelative(x = rowX[row],rowY[row])
                rowX[row] += placeable.width
            }
        }
    }
}

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text:String){
    Card(
        modifier = modifier,
        border = BorderStroke(color = Color.Black, width = Dp.Hairline),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row (
            modifier = Modifier.padding(start = 8.dp,top=4.dp, end = 8.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
                ){
            Box (modifier = Modifier
                .size(16.dp, 16.dp)
                .background(MaterialTheme.colors.secondary))
            Spacer(modifier = Modifier.width((4.dp)))
            Text(text = text)
        }
    }
}

@Composable
fun StaggeredGridBodyCOntent(modifier: Modifier = Modifier){
    Row(modifier = modifier
        .background(color = Color.LightGray)
        .padding(16.dp)
        .horizontalScroll(
            rememberScrollState()
        ),
    content = {
        StaggeredGrid(modifier) {
            for (topic in topics){
                Chip(modifier = Modifier.padding(8.dp) ,text = topic)
            }
        }
    })

}