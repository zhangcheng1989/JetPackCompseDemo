package com.example.myapplication.two

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.todo.TodoItem
import com.example.myapplication.todo.gataGeneratorsRandomTodoItem
import kotlin.random.Random

@Composable
fun TodoScreen(
    todos:List<TodoItem>,
    onAddItem: (TodoItem) -> Unit,
    onRemoveItem: (TodoItem) -> Unit
) {
    Column {

        TodoItemInputBackground(elevate = true) {
            TodoItemEntryInput(onItemCmplete = onAddItem)
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(top = 8.dp)
        ){
            items(todos){ it ->

                if (it.task == "222"){
                    TodoItemInLineEditor(
                        item = it,
                        onEditItemChange = {},
                        onEditDone = {

                        }) {

                    }
                }else{
                    TodoRow(
                        todo = it,
                        onItemClicked = { onRemoveItem(it) },
                         modifier = Modifier.fillParentMaxWidth()
                    )
                }

                TodoRow(it,
                    onItemClicked = {onRemoveItem(it)},
                    modifier = Modifier.fillParentMaxWidth()
                )
            }
        }
        
        Button(onClick = {
                onAddItem(gataGeneratorsRandomTodoItem())
        },
        modifier = Modifier
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
        val iconAlpha:Float = remember(todo.id) {
            randomTint()
        }
        Icon(
            imageVector = todo.icon.imageVector,
            tint = LocalContentColor.current.copy(alpha = iconAlpha),
            contentDescription = stringResource(id = todo.icon.contentDescription)
        )
    }
}

private fun randomTint():Float {
    return Random.nextFloat().coerceIn(0.3f,0.9f)
}