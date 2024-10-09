package com.selfpro.realies.viewer

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState
import com.selfpro.realies.util.common.SpColor
import com.selfpro.realies.util.common.SpLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MajorViewerScreen(navHostController: NavHostController, newsUrl: String) {
    if ("viewModel.url" != null) {
        val webViewState =
            rememberWebViewState(
                url = newsUrl,
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
                navHostController.popBackStack()
            }

            if (webViewState.isLoading) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(shimmerEffect())
//                )
            } else {
                SpLog.d(webViewState.isLoading)
            }

            CustomExpandableFAB(
                modifier = Modifier,
                onItemClick = {}
            )
        }
    }
}

data class FABItem(val icon: ImageVector, val text: String)

@Composable
fun CustomExpandableFAB(
    modifier: Modifier = Modifier,
    items: List<FABItem> = listOf(
        FABItem(icon = Icons.Rounded.Done, text = "done"),
        FABItem(icon = Icons.Rounded.Done, text = "done")
    ),
    onItemClick: (FABItem) -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {

    var menuExpand by remember { mutableStateOf(true) }
    var columnExpand by remember { mutableStateOf(true) }

    val onClick = {
        coroutineScope.launch {
            if (menuExpand && columnExpand) {
                columnExpand = false
                delay(200)
                menuExpand = false
            } else {
                menuExpand = true
                delay(200)
                columnExpand = true
            }
        }
    }

    val interactionSource = remember { MutableInteractionSource() }

    val buttonBackgroundColor = if (columnExpand) SpColor.BoxGray else SpColor.Void
//    val backgroundColor = if (columnExpand) SpColor.Black.copy(alpha = 0.3f) else SpColor.Void

    val backgroundColor by animateColorAsState(
        targetValue = if (columnExpand) SpColor.Black.copy(alpha = 0.3f) else SpColor.Void,
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 1000) // 애니메이션 시간 설정
    )


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .padding(bottom = 63.dp, end = 15.dp)
    ) {
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.BottomEnd),
            visible = columnExpand,
            enter = expandVertically(),
            exit = shrinkVertically(
                shrinkTowards = Alignment.Bottom
            )
        ) {
            Box(contentAlignment = Alignment.BottomEnd) {
                Column(
                    Modifier
                        .padding(bottom = 32.dp)
                        .background(
                            color = buttonBackgroundColor,
                            shape = RoundedCornerShape(
                                topStart = 12.dp,
                                topEnd = 12.dp,
                            )
                        )
                        .padding(bottom = 32.dp)
                ) {
                    repeat(items.size) { i ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null
                                ) { onItemClick(items[i]) }
                        ) {
                            Icon(
                                imageVector = items[i].icon,
                                tint = SpColor.StrokeGray,
                                contentDescription = null,
                                modifier = Modifier
                                    .height(64.dp)
                                    .width(64.dp)
                                    .padding(18.dp)
                            )
                            Text(
                                text = items[i].text,
                                modifier = Modifier
                                    .width(60.dp),
                                color = SpColor.StrokeGray
                            )
                        }
                    }

                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .clickable(interactionSource = interactionSource, indication = null) {
                    onClick()
                }
                .background(color = SpColor.Theme, shape = RoundedCornerShape(100.dp))
        ) {

            Icon(
                imageVector = Icons.Rounded.Add,
                tint = SpColor.BoxGray,
                contentDescription = null,
                modifier = Modifier
                    .height(64.dp)
                    .width(64.dp)
                    .padding(18.dp)
            )
            AnimatedVisibility(visible = menuExpand) {
                Text(
                    text = "메뉴",
                    modifier = Modifier
                        .width(60.dp),
                    color = SpColor.BoxGray
                )
            }

        }
    }
}

@Preview
@Composable
fun CustomExpandableFABPreview() {
    CustomExpandableFAB(
        onItemClick = {}
    )
}