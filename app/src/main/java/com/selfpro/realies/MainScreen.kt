package com.selfpro.realies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.selfpro.realies.sign.SignScreen
import com.selfpro.realies.util.common.SpColor

@Composable
fun MainScreen() {
    SignScreen(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.systemBars)
            .fillMaxSize(),
        isSignIn = true,
    ) {
        val navHostController: NavHostController = rememberNavController()

        Column {
            MainNavHost(
                navHostController = navHostController,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            )
            BottomNavigationBar(
                navController = navHostController,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SpColor.White)
                    .height(48.dp)
            )
        }
    }
}