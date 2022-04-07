package com.example.myapplication.todo

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myapplication.R
import java.util.*

data class  TodoItem (val task:String,val icon: TodoIcon = TodoIcon.Default,val id:UUID = UUID.randomUUID())

enum class TodoIcon(
    val imageVector: ImageVector,
    @StringRes val contentDescription:Int
){
    Square(Icons.Default.CropSquare, R.string.Expand),
    Done(Icons.Default.Done,R.string.Done),
    Event(Icons.Default.Event,R.string.Event),
    Privacy(Icons.Default.PrivacyTip,R.string.Privacy),
    Trash(Icons.Default.RestoreFromTrash,R.string.Restore);

    companion object {
        val Default = Square
    }
}