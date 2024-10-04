package com.selfpro.realies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.selfpro.realies.feature.main.sign.SignInScreen
import com.selfpro.realies.util.common.SpColor
import com.selfpro.realies.util.common.SpLog

@Composable
fun MainScreen() {
    val navHostController: NavHostController = rememberNavController()

    Column(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.systemBars)
            .background(SpColor.White)
    ) {
        SignInScreen(
            modifier = Modifier.weight(1f),
            isSignIn = true,
        ) {
            MainNavHost(
                navHostController = navHostController,
                modifier = Modifier.fillMaxWidth()
            )
        }
        BottomNavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(SpColor.White)
                .height(48.dp),
            navController = navHostController,
        )
    }
}