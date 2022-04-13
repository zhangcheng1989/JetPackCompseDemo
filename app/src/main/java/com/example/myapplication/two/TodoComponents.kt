package com.example.myapplication.two

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.todo.TodoIcon
import com.example.myapplication.todo.TodoItem


@Composable
fun TodoItemInLineEditor(
    item:TodoItem,
    onEditItemChange:(TodoItem) -> Unit,
    onEditDone:() -> Unit,
    onRemoveItem:() ->Unit
){
    TodoItemInput(
        text = item.task,
        onTextChange = {onEditItemChange(item.copy(task = it))} ,
        icon = item.icon,
        onIconChange = {onEditItemChange(item.copy(icon = it))} ,
        submit = onEditDone,
        iconVisible = true,
        buttonSlot = {
            Row {
               val shrinkButtons = Modifier.widthIn(20.dp)
               TextButton(onClick = onRemoveItem, modifier = shrinkButtons) {
                   Text(
                       text = "\uD83D\uDCBE",
                       textAlign = TextAlign.End,
                       modifier = Modifier.width(30.dp)
                   )
               } 
            }
        }
    )
}

@Composable
fun TodoItemInputBackground(
    elevate:Boolean,
    modifier: Modifier = Modifier,
    content:@Composable RowScope.() -> Unit
){
    Surface(
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.05f),
        shape = RectangleShape
    ) {
        val animateElevation by animateDpAsState(if (elevate) 1.dp else 0.dp, TweenSpec(durationMillis = 500))
        Surface(
            color = MaterialTheme.colors.onSurface.copy(0.05f),
            elevation = animateElevation,
            shape = RectangleShape
        ) {
            Row(
                modifier = modifier.animateContentSize(animationSpec = TweenSpec(300)),
                content = content
            )
        }
    }
}

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
fun TodoItemEntryInput(onItemCmplete:(TodoItem) -> Unit){

    val(text,setText) = remember {
        mutableStateOf("")
    }

    val(icon,setIcon) = remember {
        mutableStateOf(TodoIcon.Default)
    }

    val iconsVisible = text.isNotBlank()

    val submit = {
        onItemCmplete(TodoItem(text))
        setIcon(TodoIcon.Default)
        setText("")
    }

    TodoItemInput(
        text = text,
        onTextChange = setText,
        icon = icon,
        onIconChange = setIcon,
        submit = submit,
        iconVisible = iconsVisible
    ){
        TodoEditButton(
            onClick = submit,
            "Add",
            enabled = text.isNotBlank()
        )
    }
}


@Composable
fun TodoItemInput(
    text: String,
    onTextChange: (String) -> Unit,
    icon: TodoIcon,
    onIconChange: (TodoIcon) -> Unit,
    submit: () -> Unit,
    iconVisible: Boolean,
    buttonSlot:@Composable () -> Unit
) {

    // 这个函数使用remember 给自己添加内存，然后在内存中存储一个由mutableStateOf 创建的 mutableStateOf<String>
    //它是compose 的内置类型，提供一个可观察的状态持有者
    //val(text,setText) = remember {
    //       mutableStateOf("")
    //    }
    //对value 的任何更改都将自动重新组合读取此状态的任何可组合函数


//    val(text,setText) = remember {
//       mutableStateOf("")
//    }
//
//    val(icon,setIcon) = remember {
//        mutableStateOf(TodoIcon.Default)
//    }
//
//    //iconrow 是否可见
//    val iconsVisible = text.isNotBlank()
//
//
//    val submit = {
//        onItemCmplete(TodoItem(text))
//        setIcon(TodoIcon.Default)
//        setText("")
//    }


    Column {
        Row(
            Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            TodoInputText(
                text,
                onTextChange = onTextChange,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                onItemAction = submit
            )

            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.align(Alignment.CenterVertically)){
                buttonSlot()
            }
        }

        if (iconVisible){
            AnimatedIconRow(icon = icon, onIconChange = onIconChange, modifier = Modifier.padding(8.dp) )
        }else{
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TodoInputText(
    text:String,
    onTextChange:(String) -> Unit,
    modifier: Modifier = Modifier,
    onItemAction:() -> Unit = {}
){

    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        maxLines = 1,
        modifier = modifier,
        //配置软键盘
        keyboardOptions = KeyboardOptions.Default.copy(imeAction =  ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onItemAction()
            keyboardController?.hide() //点击完成隐藏键盘
        })

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