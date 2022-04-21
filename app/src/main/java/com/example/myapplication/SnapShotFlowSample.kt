package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

/**
 * 使用 SnapshotFlow 可以将 State 对象转换为flow snapshotFlow 会运行传入 block 并发出从块中读取的State 对象的结果，当在snapshotFlow 块中读取的State 对象 之一发生变化时，如果新值与之前发出的值不相等，Flow会向其收集器发出新值
 */

@Composable
fun SnapShotFlowSample() {
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        items(1000) {
            Text(text = "Item $it")
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow {
            listState.firstVisibleItemIndex
        }
            .filter { it > 20 }
            .distinctUntilChanged()
            .collect{
                Log.d("zc","firstVisibleItemIndex $it")
            }
    }
}