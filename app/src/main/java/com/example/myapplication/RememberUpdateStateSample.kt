package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import kotlinx.coroutines.delay

/**
 *
 * 如果key 值有更新，那么LaunchedEffect 在重组时就会被重新启动。但是有时候需要在Launchedeffect中使用最新的参数值，但是又不想重新启动LaunchedEffect，此时就需要用到rememberUpdatedState
 * rememberUpdatedState 的作用时给某个参数创建一个引用，来跟踪这些参数，并保证其值被使用时是最新值，参数被改变时不重启effect
 */

@Composable
fun RememberUpdateStateSample() {

    val onTimeOut1: () -> Unit = {
        Log.d("zc", "landing onTimeOut1")
    }

    val onTimeOut2: () -> Unit = {
        Log.d("zc", "landing onTimeOut2")
    }


    val changeOnTimeOutState = remember {
        mutableStateOf(onTimeOut1)
    }

    Column() {
        Button(onClick = {
            if (changeOnTimeOutState.value  == onTimeOut1){
                changeOnTimeOutState.value = onTimeOut2
            }else{
                changeOnTimeOutState.value = onTimeOut1

            }
        }) {
            Text(text = "choose onTimeOut ${if (changeOnTimeOutState.value == onTimeOut1) 1 else 2 }")
        }

        LandingScreen(changeOnTimeOutState.value)
    }
}


@Composable
fun LandingScreen(onTimeOut: () -> Unit) {
    
    val currentOnTimeout by rememberUpdatedState(newValue = onTimeOut)
    
    LaunchedEffect(Unit) {
        Log.d("zc", "LaunchedEffect")
        repeat(10) {
            delay(1000L)
            Log.d("zc", "delay ${it + 1}s")
        }
        currentOnTimeout()
    }
}
