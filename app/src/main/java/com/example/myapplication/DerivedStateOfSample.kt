package com.example.myapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/***
 * 如果某个状态是从其他状态对象计算或派生得出的，请使用derivedStateOf 作为条件的状态我们称为条件状态，当任意一个条件状态更新时，结果状态都会重新计算
 */
@Composable
fun DerivedStateOfSample() {
    TodoList()
}

fun String.containsWord(list: List<String>): Boolean {
    for (item in list) {
        if (this.contains(item)) return true
    }

    return false
}

@SuppressLint("RememberReturnType")
@Composable
fun TodoList(
    highPrioritywords: List<String> = listOf("Review", "Unblock", "Compose")
) {

    val (text, setText) = remember {
        mutableStateOf("")
    }

    val todoTasks = remember {
        mutableStateListOf<String>()
    }

    val highPriorityTask = remember(todoTasks, highPrioritywords) {
        derivedStateOf {
            todoTasks.filter { it.containsWord(highPrioritywords) }
        }
    }

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(value = text, onValueChange = setText)
            Button(onClick = {
                todoTasks.add(text)
                setText("")

            }) {
                Text(text = "Add")
            }
        }

        LazyColumn {

            items(highPriorityTask.value) {
                ItemText(text = it, modifier = Modifier.background(Color.LightGray))
            }

            items(todoTasks) {
                ItemText(text = it)
            }
        }
    }
}


@Composable
fun ItemText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        modifier = modifier
            .height(30.dp)
            .fillMaxWidth()
    )
}
