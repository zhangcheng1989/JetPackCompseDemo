package com.example.myapplication

import android.content.Context
import android.location.LocationManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyLocationObserver(val mContext:Context):LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun startLocation(){
        val locationManager:LocationManager = mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,1,object ::)

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun stopLocation(){

    }
}