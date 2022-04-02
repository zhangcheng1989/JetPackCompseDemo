package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.*
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import kotlinx.coroutines.launch

@Composable
fun SimpleColumn() {
    
    Column {
        repeat(100){
            Text(text = "Item #$it",style = MaterialTheme.typography.subtitle1)
        }
    }
}


@Composable
fun SimpleList(){
    val scrollState = rememberScrollState()
    Column(Modifier.verticalScroll(scrollState)) {
        repeat(100){
            Text(text = "Item #$it",style = MaterialTheme.typography.subtitle1)
        }
    }
}

@Composable
fun LazyList(){
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState){
        items(100){
            Text(text = "Item #$it",style = MaterialTheme.typography.subtitle1)
        }
    }
}

@Composable
fun ScrollingList(){
    val listSize = 100
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    Column {
        Row {
           Button(
               modifier = Modifier.weight(1f),
               onClick = {
                   coroutineScope.launch {
                       scrollState.animateScrollToItem(0)
                   }
               }) {
               Text(text = "Scroll to the top")
           }

            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(listSize - 1)
                    }
                }) {
                Text(text = "Scroll to the end")
            }
        }

        LazyColumn(state = scrollState){
            items(100){
                ImageListItem(index = it)
            }
        }
    }
}

@Composable
fun ImageListItem(index:Int){
    val painter = rememberImagePainter(data = "https://www.baidu.com/img/flexible/logo/pc/result@2.png")
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Item#$index",style = MaterialTheme.typography.subtitle1)
    }
}
