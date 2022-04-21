package com.example.myapplication

import android.graphics.Point
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
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

data class Message(val author:String,val body:String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val samllestSize = Paint()
//        val largestSize = Point()
//        val realSize = Point()
//        val display = windowManager.defaultDisplay
//        display.getCurrentSizeRange(samllestSize!)


        setContent {
            MyApplicationTheme {
//                DisoisableEffectSample(onBackPressedDispatcher)

                SnapShotFlowSample()
            }
//            MyApplicationTheme {
//                ScrollingList()
                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
////                    MessageCard(msg = Message("Android","android"))
////                    Conversation(listOf(Message("android","android"),Message("ios","iosiosiosiosiosiosiosiosiosiosiosiosiosiosiosiosiosiosiosiosiosiosiosiosiosiosiosiosiosiosios")))
//                }

//}
}
}
}

@Composable
fun MessageCard(msg:Message){
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_round),
        contentDescription = null,
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)

    )
    Spacer(modifier = Modifier.width(8.dp))
    var isExpanded by remember {
        mutableStateOf(false)
    }
    val surfaceColor:Color by animateColorAsState(
        if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    )
    Column(
        modifier = Modifier.clickable {
            isExpanded = !isExpanded
        }
    ) {
        Text(
            text = msg.author,
            color = MaterialTheme.colors.secondaryVariant
        )
        Spacer(modifier = Modifier.height(4.dp))
        Surface(
            shape = MaterialTheme.shapes.medium,
            elevation = 1.dp,
            color =  surfaceColor,
            modifier = Modifier
                .animateContentSize()
                .padding(1.dp)
        ) {
            Text(
                text = msg.body,
                color = MaterialTheme.colors.secondary,
                maxLines = if (isExpanded) Int.MAX_VALUE else 1
            )
        }
    }
}
}

@Composable
fun Conversation(messages:List<Message>){
    LazyColumn{
        items (messages){ msg ->
            MessageCard(msg = msg)
        }
    }
}

@Composable
fun Greeting(name: String) {
//    Text(text = "Hello $name!")
   Column{
       Button(onClick = { /*TODO*/ }) {
           Text(text = "开始")
       }

       Button(onClick = { /*TODO*/ }) {
           Text(text = "停止")
       }
   }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        SimpleColumn()
//        MessageCard(msg = Message("Android","android"))
    }
}