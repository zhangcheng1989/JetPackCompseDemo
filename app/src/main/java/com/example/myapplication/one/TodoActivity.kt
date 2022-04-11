package com.example.myapplication.one

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import com.example.myapplication.todo.TodoItem
import com.example.myapplication.ui.theme.MyApplicationTheme

data class Message(val author:String,val body:String)

class TodoActivity : ComponentActivity() {

    private val todoViewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                TodoItemInput({
                    Log.i("zc",it.task)
                })
            }
        }
    }

    @Composable
    private fun TodoActivityScreen(){
//        val items = listOf<TodoItem>(
//            TodoItem("Learn compose",TodoIcon.Event),
//            TodoItem("Take the codelab"),
//            TodoItem("Apply state",TodoIcon.Done),
//            TodoItem("Build dynamic UIS",TodoIcon.Square),
//        )
        val items:List<TodoItem> by todoViewModel.todoItems.observeAsState(listOf())

        TodoScreen(items,
        onAddItem = {todoViewModel.addItem(it)},
         onRemoveItem = { todoViewModel.removeItem(it) }
        )
    }

}

