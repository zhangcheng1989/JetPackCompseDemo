package com.example.myapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch


/**
 * 由于当LaunchedEffect 是可组合函数，因此只能在其他可组合函数中使用，想要在可组合项目外启动协程，且需要对这个协程存在作用域限制，以便协程在退出组合后自动取消，可以使用rememberCoroutineScope
 * 此外，如果需要手动控制一个或多个协程的生命周期，请使用rememberCoroutineScope
 */
@Composable
fun ScaffoldSample() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "抽屉组件内容")
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "脚手架示例")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
                    }
                }
            )
        },
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "屏幕内容区域")
            }
        },

        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(text = "悬浮按钮")
                },
                onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("点击了悬浮按钮")
                    }
                })
        }
    )
}