package com.selfpro.realies.feature.main.news

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState
import com.selfpro.realies.SharedViewModel
import com.selfpro.realies.util.SpLog
import com.selfpro.realies.util.shimmerEffect.shimmerEffect

@Composable
fun NewsScreen(sharedViewModel: SharedViewModel) {
    if (sharedViewModel.url != null) {
        val webViewState =
            rememberWebViewState(
                url = sharedViewModel.url!!,
                additionalHttpHeaders = emptyMap()
            )
        val webChromeClient = AccompanistWebChromeClient()
        val webViewNavigator = rememberWebViewNavigator()

        val webViewClient = AccompanistWebViewClient()


        Box(modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        SpLog.d("onDoubleTap")
                    }
                )
            }) {
            WebView(
                state = webViewState,
                client = webViewClient,
                chromeClient = webChromeClient,
                navigator = webViewNavigator,
                onCreated = { webView ->

                    with(webView) {
                        settings.run {
                            javaScriptEnabled = true
                            domStorageEnabled = true
                            javaScriptCanOpenWindowsAutomatically = false
                        }
                    }
                })

            BackHandler(enabled = true) {
                sharedViewModel.mainNavController.popBackStack()
            }

            if (webViewState.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(shimmerEffect())
                )
            } else {
                SpLog.d(webViewState.isLoading)
            }
        }
    }
}
