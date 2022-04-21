package com.example.myapplication.coroutine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.coroutine.ui.Corountie01
import com.example.myapplication.ui.theme.MyApplicationTheme

class CoroutineActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Corountie01()
            }
        }
    }
}