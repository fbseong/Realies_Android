package com.selfpro.realies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.graphics.toArgb
import com.selfpro.realies.util.common.SpColor
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalAnimationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val window = this.window
            window.statusBarColor = SpColor.White.toArgb()
            window.navigationBarColor = SpColor.White.toArgb()

            MainScreen()
        }
    }
}