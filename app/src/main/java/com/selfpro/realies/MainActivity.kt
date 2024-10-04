package com.selfpro.realies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.selfpro.realies.add.addScreen
import com.selfpro.realies.challenge.challengeScreen
import com.selfpro.realies.data.model.NavData
import com.selfpro.realies.my.myScreen
import com.selfpro.realies.realies.RealiesRoute
import com.selfpro.realies.realies.realiesScreen
import com.selfpro.realies.search.searchScreen
import com.selfpro.realies.subscribe.subscribeScreen
import com.selfpro.realies.util.common.SpColor
import com.selfpro.realies.util.common.SpLog
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