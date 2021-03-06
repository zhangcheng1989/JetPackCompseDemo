package com.example.myapplication.one

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.todo.TodoIcon
import com.example.myapplication.todo.TodoItem


@Composable
fun AnimatedIconRow(
    icon:TodoIcon,
    onIconChange:(TodoIcon) -> Unit,
    modifier: Modifier = Modifier,
    visible:Boolean = true,
){
    val enter = remember {
        fadeIn(animationSpec = TweenSpec(300, easing = FastOutLinearInEasing))
    }

    val exit = remember {
        fadeOut(animationSpec = TweenSpec(100, easing = FastOutSlowInEasing))
    }

    Box(modifier.defaultMinSize(minHeight = 16.dp)){
        AnimatedVisibility(
            visible = visible,
            enter = enter,
            exit = exit
        ) {
            IconRow(icon = icon,onIconChange = onIconChange)
        }
    }
}

@Composable
fun IconRow(
    icon: TodoIcon,
    onIconChange: (TodoIcon) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier ) {
        for(todoIcon in TodoIcon.values()){
            SelectableIconButton(
                icon = todoIcon.imageVector,
                iconContentDescription = todoIcon.contentDescription,
                onIconSelected = {onIconChange(todoIcon)},
                isSelected = (todoIcon == icon)
            )
        }
    }
}

@Composable
fun SelectableIconButton(
    icon: ImageVector,
    iconContentDescription: Int,
    onIconSelected: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {

    val tint = if (isSelected) {
        MaterialTheme.colors.primary
    }else{
        MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
    }

    TextButton(
        onClick = {
            onIconSelected()
        },
        shape = CircleShape,
        modifier = modifier
    ) {
        Column {
            Icon(
                imageVector = icon,
                tint = tint,
                contentDescription = stringResource(id = iconContentDescription)
            )

            if (isSelected){
                Box(
                    Modifier
                        .padding(top = 3.dp)
                        .width(icon.defaultWidth)
                        .height(1.dp)
                        .background(tint)
                ) {

                }
            }else{
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}


@Composable
fun TodoItemInput(onItemCmplete:(TodoItem) -> Unit) {

    // ??????????????????remember ?????????????????????????????????????????????????????????mutableStateOf ????????? mutableStateOf<String>
    //??????compose ?????????????????????????????????????????????????????????
    //val(text,setText) = remember {
    //       mutableStateOf("")
    //    }
    //???value ??????????????????????????????????????????????????????????????????????????????


    val(text,setText) = remember {
       mutableStateOf("")
    }

    val(icon,setIcon) = remember {
        mutableStateOf(TodoIcon.Default)
    }

    //iconrow ????????????
    val iconsVisible = text.isNotBlank()

    Column {
        Row(
            Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            TodoInputText(
                text,
                onTextChange = setText,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )

            TodoEditButton(
                onClick =
                {
                    onItemCmplete(TodoItem(text))
                    setText("")
                },
                "Add",
                modifier = Modifier.align(Alignment.CenterVertically),
                enabled = text.isNotBlank()
            )
        }

        if (iconsVisible){
            AnimatedIconRow(icon = icon, onIconChange = setIcon, modifier = Modifier.padding(8.dp) )
        }else{
            Spacer(modifier = Modifier.height(16.dp))
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