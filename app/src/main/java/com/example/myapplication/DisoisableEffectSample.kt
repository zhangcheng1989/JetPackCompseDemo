package com.example.myapplication

import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*

@Composable
fun BackHandler(
    backDispatcher: OnBackPressedDispatcher,
    onBack: () -> Unit
) {

    val currentOnBack by rememberUpdatedState(newValue = onBack)

    val backCallback = remember {   //缓存，不用重复创建
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                currentOnBack()
            }
        }
    }

    DisposableEffect(backDispatcher) {
        backDispatcher.addCallback(backCallback)
        onDispose {
            Log.d("zc", "onDispose")
            backCallback.remove()
        }
    }
}

@Composable
fun DisoisableEffectSample(backDispatcher: OnBackPressedDispatcher) {
    var addBackCallback by remember {
        mutableStateOf(false)
    }
    Row {
        Switch(
            checked = addBackCallback,
            onCheckedChange = {
                addBackCallback = !addBackCallback
            }
        )

        Text(text = if (addBackCallback) "Add Back Call back " else "Not Add Back Callback")
    }

    if (addBackCallback) {
        BackHandler(backDispatcher) {
            Log.d("zc", "Back")
        }
    }
}