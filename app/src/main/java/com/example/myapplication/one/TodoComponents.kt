package com.example.myapplication.one

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.todo.TodoItem

@Composable
fun TodoComponents(onItemCmplete:(TodoItem) -> Unit) {

    val(text,setText) = remember {
       mutableStateOf("")
    }

    Column {
        Row(
            Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            TodoInputText(
                text,
                onTextChange = setText,
                modifier = Modifier.weight(1f)
                    .padding(8.dp)
            )

            TodoEditButton(
                onClick = {},
                "Add",
                modifier = Modifier.align(Alignment.CenterVertically),
                enabled = text.isNotBlank()
            )
        }
    }
}

@Composable
fun TodoInputText(
    text:String,
    onTextChange:(String) -> Unit,
    modifier: Modifier = Modifier
){
    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        maxLines = 1,
        modifier = modifier
    )
}

@Composable
fun TodoEditButton(
    onClick:() -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled:Boolean = true
){
    TextButton(
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(),
        modifier = modifier,
        enabled = enabled
    ) {
        Text(text = text)
    }
}