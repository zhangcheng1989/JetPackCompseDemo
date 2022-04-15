package com.example.myapplication

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope

@Composable
fun Home() {
    LaunchedEffect(Unit){

    }

    val lazyListState = rememberLazyListState()

    val coroutineScope = rememberCoroutineScope()
    Scaffold(topBar = {

    }) {

    }
}