package com.example.myapplication

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myapplication.repository.Image
import com.example.myapplication.repository.ImageRepository
import com.example.myapplication.repository.Result


@SuppressLint("ProduceStateDoesNotAssignValue")
@Composable
fun loadNetworkImage(url: String, imageRepository: ImageRepository): State<Result<Image>> {

    return produceState(initialValue = Result.Loading as Result<Image>, url, imageRepository) {
        Log.d("zv", "thread:${Thread.currentThread().name}")
        val image = imageRepository.load(url)
        value = if (image == null) {
            Result.Error
        } else {
            Result.Success(image = image)
        }
    }
}


@Composable
fun ProduceStateSample() {

    val images = listOf(
        "https://pics5.baidu.com/feed/faf2b2119313b07e163dc8a3e5d1652596dd8ca3.png?token=37ba64e3f69a7a42d8b01d95a5079aae",
        "https://pics5.baidu.com/feed/faf2b2119313b07e163dc8a3e5d1652596dd8ca3.png?token=37ba64e3f69a7a42d8b01d95a5079aae",
        "https://故意pics5.baidu.com/feed/faf2b2119313b07e163dc8a3e5d1652596dd8ca3.png?token=37ba64e3f69a7a42d8b01d95a5079aae"
    )
    var index by remember {
        mutableStateOf(0)
    }

    val imageRepository = ImageRepository(LocalContext.current)
    val result = loadNetworkImage(url = images[index], imageRepository = imageRepository)

    Column {
        Button(onClick = {
            index %= images.size
            if (++index == images.size) index = 0
        }) {
            Text(text = "选择第 $index 张图片")
        }

        when(result.value){
            is Result.Success ->{
                Image(
                    bitmap = (result.value as Result.Success).image.imageBitmap,
                    contentDescription = "image load success"
                )
            }

            is Result.Error ->{
                Image(
                    imageVector = Icons.Rounded.Warning,
                    contentDescription = "image load error",
                    modifier = Modifier.size(200.dp,200.dp)
                )
            }
            else ->{
                CircularProgressIndicator()
            }
        }

    }


}