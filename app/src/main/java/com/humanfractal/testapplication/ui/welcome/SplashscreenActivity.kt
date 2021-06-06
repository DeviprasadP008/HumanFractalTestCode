package com.humanfractal.testapplication.ui.welcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.humanfractal.testapplication.R
import com.humanfractal.testapplication.ui.Home.HomeActivity
import com.humanfractal.testapplication.ui.startNewActivity
import java.util.*

class SplashscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                val activity = HomeActivity::class.java
                startNewActivity(activity)
            }
        }, 3000)

    }
}