package com.example.myapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun ScaffoldSample(
    state:MutableState<Boolean>,
    scaffoldState: ScaffoldState = rememberScaffoldState()
){

    LaunchedEffect(state.value){
        scaffoldState.snackbarHostState.showSnackbar(
            message = "Error message",
            actionLabel = "Retry message"
        )
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {Text(text = "脚手架")}
            ) 
        },
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Button(onClick = {
                    state.value = !state.value
                }) {
                    Text(text = "Error occurs")
                }
            }
        }
    )
}

@Composable
fun LaunchedEffectSample() {

    val state = remember {
        mutableStateOf(false)
    }

    ScaffoldSample(state)
}