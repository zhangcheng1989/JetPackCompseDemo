package com.example.myapplication

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {
        val (button,text) = createRefs()
        
        Button(
            modifier = Modifier.constrainAs(button){
                top.linkTo(parent.top, margin = 16.dp)
            },
            onClick = { /*TODO*/ }) {
            Text(text = "Button")
        }
        
        Text(text = "Text", Modifier.constrainAs(text) {
            top.linkTo(button.bottom, margin = 16.dp)
            centerHorizontallyTo(parent)
        })
    }
}

@Composable
fun ConstraintLayoutContent2() {
    ConstraintLayout {
        val (button1,button2,text) = createRefs()
        Button(
            onClick = { /*TODO*/ },
            Modifier.constrainAs(button1){
                top.linkTo(parent.top, margin = 16.dp)
            }
        ){
            Text(text = "Button 1")
        }

        Text(text = "Text",Modifier.constrainAs(text){
            top.linkTo(button1.bottom, margin = 16.dp)
            centerAround(button1.end)
        })

        val barrier = createEndBarrier(button1,text)
        Button(
            onClick = { /*TODO*/ },
            Modifier.constrainAs(button2){
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(barrier)
            }
        ){
            Text(text = "Button 2")
        }
    }
}

@Composable
fun LargeLayoutContent(){
    ConstraintLayout {
        val text = createRef()
        val guideline = createGuidelineFromStart(0.5f)
        Text(text = "constrainAsconstrainAsconstrainAsconstrainAsconstrainAsconstrainAsconstrainAsconstrainAsconstrainAsconstrainAsconstrainAsconstrainAsconstrainAsconstrainAsconstrainAs",
        Modifier.constrainAs(text){
            linkTo(start=guideline, end = parent.end)
            width = Dimension.preferredWrapContent
        })
    }
}


@Composable
fun DecoupleContentLayout(){
    val margin = 16.dp
    ConstraintLayout {
        val (button,text) = createRefs()

        Button(
            modifier = Modifier.constrainAs(button){
                top.linkTo(parent.top, margin = margin)
            },
            onClick = { /*TODO*/ }) {
            Text(text = "Button")
        }

        Text(text = "Text", Modifier.constrainAs(text) {
            top.linkTo(button.bottom, margin = margin)
            centerHorizontallyTo(parent)
        })
    }

}


@Composable
fun DecoupleContentLayout2(){
    BoxWithConstraints {
        val constraints = if (maxWidth <maxHeight) {
            decoupledConstraints(16.dp)
        }else{
            decoupledConstraints(160.dp)
        }
        ConstraintLayout (constraints){
            Button(
                modifier = Modifier.layoutId("button"),
                onClick = { /*TODO*/ }) {
                Text(text = "Button")
            }

            Text(text = "Text", Modifier.layoutId("text"))
        }
    }
}

private fun decoupledConstraints(margin:Dp) :ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")
        constrain(button){
            top.linkTo(parent.top,margin)
        }

        constrain(text){
            top.linkTo(button.bottom,margin)
//            centerHorizontallyTo(button)
        }
    }
}