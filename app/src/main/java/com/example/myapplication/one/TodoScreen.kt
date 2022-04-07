package com.example.myapplication.one

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.todo.TodoItem
import com.example.myapplication.todo.gataGeneratorsRandomTodoItem

@Composable
fun TodoScreen(
    todos:List<TodoItem>,
    onAddItem: (TodoItem) -> Unit,
    onRemoveItem: (TodoItem) -> Unit
) {
    Column {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(top = 8.dp)
        ){
            items(todos){ it ->
                TodoRow(it,
                    onItemClicked = {onRemoveItem(it)},
                    modifier = Modifier.fillParentMaxWidth()
                )
            }
        }
        
        Button(onClick = {
                onAddItem(gataGeneratorsRandomTodoItem())
        },
        modifier =  Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
            Text(text = "Add random item")
        }
    }
}


@Composable
fun TodoRow(
    todo:TodoItem,
    onItemClicked: (TodoItem) -> Unit,
    modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clickable {
                onItemClicked(todo)
            }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween //子元素均匀分布
    ) {
        Text(text = todo.task)
        Icon(imageVector = todo.icon.imageVector, contentDescription = stringResource(id = todo.icon.contentDescription))
    }
}