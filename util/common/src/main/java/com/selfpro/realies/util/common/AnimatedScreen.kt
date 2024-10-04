package com.selfpro.realies.util.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable

@Composable
fun AnimateScreen(currentScreen: String, targetScreen: String, content: @Composable () -> Unit) {
    // AnimatedVisibility를 사용하여 화면 전환 애니메이션
    AnimatedVisibility(
        visible = currentScreen == targetScreen,
        enter = fadeIn(animationSpec = tween(durationMillis = 300)) + slideInHorizontally(
            animationSpec = tween(durationMillis = 300),
            initialOffsetX = { 100 } // 오른쪽에서 들어오기
        ),
        exit = fadeOut(animationSpec = tween(durationMillis = 300)) + slideOutHorizontally(
            animationSpec = tween(durationMillis = 300),
            targetOffsetX = { -100 } // 왼쪽으로 나가기
        )
    ) {
        content()
    }
}