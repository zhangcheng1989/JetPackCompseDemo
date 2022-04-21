package com.example.myapplication.coroutine.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.example.myapplication.coroutine.api.UserServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun Corountie01() {
    
    Column {
        Button(onClick = {
            GlobalScope.launch(Dispatchers.Main) {
                withContext(Dispatchers.IO){

                }
            }
        }) {
            Text(text = "提交")
        }
        
        Text(text = "hello", fontSize = 18.sp)
    }
   

    
}