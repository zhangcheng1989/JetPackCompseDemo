package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun ComposeitionSample3() {

    Column {
//       MyCard(backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.0f)) {
//
//       }
//
//        MyCard(backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.0f)) {
//
//        }
        
        CompositionLocalProvider(values = *arrayOf(LocalElevations provides  CardElevation.high)) {
            MyCard(backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.0f)) {
            }
        }


    }
}