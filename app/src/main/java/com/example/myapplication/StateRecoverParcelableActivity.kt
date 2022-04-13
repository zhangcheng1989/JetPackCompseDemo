package com.example.myapplication

import android.os.Bundle
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class StateRecoverParcelableActivity:ComponentActivity() {

    @Parcelize
    data class City(val name:String,var country:String):Parcelable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                CityScreen()
            }
        }
    }

    @Composable
    fun CityScreen() {

        val (city,setCity) = rememberSaveable {
            mutableStateOf(City("Madrud","Spain"))
        }

        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ){

            TextButton(
                onClick = {
                          setCity(City("beijing","China"))
                },
                colors = ButtonDefaults.buttonColors(),
            ) {
                Text(text = "Click to change")
            }
            
            Text(text = "${city.country} - ${city.name}")
        }
    }
}