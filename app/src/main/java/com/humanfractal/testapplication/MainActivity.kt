package com.humanfractal.testapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.humanfractal.testapplication.ui.welcome.SplashscreenActivity
import com.humanfractal.testapplication.ui.startNewActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val activity = SplashscreenActivity::class.java
        startNewActivity(activity)
    }
}