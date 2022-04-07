package com.example.myapplication.todo

fun gataGeneratorsRandomTodoItem():TodoItem {
    val message = listOf(
        "Learn compose",
        "Learn state",
        "Build dynamic UIs",
        "Learn Unidirectional Data Flow",
        "Interate LiveData",
        "Interate ViewModel",
        "Remember to savedState!",
        "Build stateless composeables",
        "Use state from stateless composeables"
    ).random()
    val icon  = TodoIcon.values().random()
    return TodoItem(message, icon = icon)
}