package com.selfpro.realies.feature.main.sign

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    isSignIn: Boolean,
    content: @Composable () -> Unit
) {
    if (isSignIn) {
        Box(modifier = modifier) {
            content()
        }
    } else {
        Box(
            modifier = modifier
                .fillMaxSize()
                .blur(radius = 7.dp)
                .clickable {
                }
        ) {
            content()
        }
    }
}