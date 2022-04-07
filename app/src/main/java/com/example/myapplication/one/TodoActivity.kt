package com.example.myapplication.one

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.todo.TodoIcon
import com.example.myapplication.todo.TodoItem
import com.example.myapplication.ui.theme.MyApplicationTheme

data class Message(val author:String,val body:String)

class TodoActivity : ComponentActivity() {
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
        val items = listOf<TodoItem>(
            TodoItem("Learn compose",TodoIcon.Event),
            TodoItem("Take the codelab"),
            TodoItem("Apply state",TodoIcon.Done),
            TodoItem("Build dynamic UIS",TodoIcon.Square),
        )
        TodoScreen(items)
    }

}

