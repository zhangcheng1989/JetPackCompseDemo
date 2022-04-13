package com.example.myapplication.two

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import com.example.myapplication.one.TodoViewModel
import com.example.myapplication.todo.TodoItem
import com.example.myapplication.ui.theme.MyApplicationTheme

data class Message(val author:String,val body:String)

class TodoActivity : ComponentActivity() {

    private val todoViewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                TodoActivityScreen()
            }
        }
    }

    @Composable
    private fun TodoActivityScreen(){

        val items:List<TodoItem> by todoViewModel.todoItems.observeAsState(listOf())

        TodoScreen(items,
        onAddItem = {todoViewModel.addItem(it)},
         onRemoveItem = { todoViewModel.removeItem(it) }
        )
    }

}

