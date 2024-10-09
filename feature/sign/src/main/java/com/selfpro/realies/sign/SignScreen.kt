package com.selfpro.realies.sign

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SignScreen(
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
            modifier = modifier.blur(radius = 7.dp)
                .pointerInput(Unit) {
                    detectTapGestures {
                        // 터치 이벤트를 처리
                    }
                }
        ) {
            content()
        }
    }
}