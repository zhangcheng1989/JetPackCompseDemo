package com.example.myapplication

import android.app.Application
import android.content.res.Configuration

class MyApplication:Application() {

    companion object{

    }

    override fun onCreate() {
        super.onCreate()
    }

    //应用退出的时候 比如闪退
    override fun onTerminate() {
        super.onTerminate()


    }

    //在配置改变时调用，例如从竖屏变为横屏
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}

