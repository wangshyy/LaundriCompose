package com.laundri.wsy

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 *  author : wsy
 *  date   : 2024/6/26
 *  desc   :
 */

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}