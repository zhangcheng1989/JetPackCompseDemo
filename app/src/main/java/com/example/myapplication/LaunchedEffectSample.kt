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

    /***
     * 如果在组合函数中进行耗时操作，就需要将耗时操作放入协程中执行,而协程需要在协程作用域中创建，因此Compose 提供了 LaunchedEffect 用于创建协程
     * 1、当LaunchedEffect 进入组件树时，会启动一个协程，并将block 放入该协程中执行
     * 2、当组合函数从视图树上 detach 时，协程还未执行完毕，该协程也会被取消执行
     * 3、当LaunchedEffect 在重组时其 key 不变，那LaunchedEffect 不会被重新启动执行 block
     * 4、当LaunchedEffect 在重组时其 key 发生了变化，则 LaunchedEffect 会执行 cancel 后，再重新启动一个新协程执行block
     *
     */

    if (state.value){
        LaunchedEffect(scaffoldState.snackbarHostState){
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Error message",
                actionLabel = "Retry message"
            )
        }
    }


//    LaunchedEffect(state.value){
//        scaffoldState.snackbarHostState.showSnackbar(
//            message = "Error message",
//            actionLabel = "Retry message"
//        )
//    }

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