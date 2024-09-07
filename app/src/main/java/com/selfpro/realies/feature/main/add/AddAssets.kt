package com.selfpro.realies.feature.main.add

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import com.google.ai.client.generativeai.type.Content
import com.selfpro.realies.R
import com.selfpro.realies.data.model.NewsAddMenuAssetsModel
import com.selfpro.realies.data.model.NewsAddMenuAssetsModel.ImageAssets
import com.selfpro.realies.data.model.NewsAddMenuAssetsModel.AIContentAssets
import com.selfpro.realies.data.model.NewsAddMenuAssetsModel.TextAssets
import com.selfpro.realies.data.model.NewsAddMenuAssetsModel.StartTextAssets
import com.selfpro.realies.data.model.NewsAddMenuModel
import com.selfpro.realies.ui.color.SpColor
import com.selfpro.realies.util.AlertScreenData
import com.selfpro.realies.util.SpLog
import com.selfpro.realies.util.shimmerLoading
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NewsContentTextField(
    lastWord: MutableState<String>,
    contentFieldValue: MutableState<TextFieldValue>,
    onStartValueChange: (TextFieldValue) -> Unit,
    onValueChange: (TextFieldValue) -> Unit,
    expanded: MutableState<Boolean>,
    coroutineScope: CoroutineScope = rememberCoroutineScope()

) {
    var cursorY = remember { mutableStateOf(0f) }
    var tl by remember { mutableStateOf(listOf<NewsAddMenuAssetsModel>(StartTextAssets)) }
    var focusRequesters by remember { mutableStateOf(listOf(FocusRequester())) }

    SpLog.d(tl)
    SpLog.d(focusRequesters)

    val density = LocalDensity.current
    val items = listOf(
        NewsAddMenuModel(
            title = "이미지",
            description = "파일을 업로드하여 추가하세요",
            image = 0
        ) {
            tl = tl.toMutableList().apply { add(ImageAssets) }
            focusRequesters = focusRequesters.toMutableList().apply { add(FocusRequester()) }
        },
        NewsAddMenuModel(
            title = "AI 자동 생성",
            description = "AI를 통해 뉴스를 완성하세요",
            image = 0
        ) {},
        NewsAddMenuModel(
            title = "소제목",
            description = "섹션 제목 설정",
            image = 0
        ) {}
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 500.dp)
            .padding(top = 8.dp, start = 15.dp, end = 15.dp)
            .background(
                color = SpColor.BoxGray,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(15.dp)
    ) {
        Column {
            repeat(tl.size) { i ->
//                SpLog.d("tl.size: ${tl.size}")
//                SpLog.d("tl: ${tl}")

                if (tl[i] == StartTextAssets) {
                    ContentStartTextField(contentFieldValue, onStartValueChange, cursorY)
                } else if (tl[i] == ImageAssets) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null
                    )
                    ContentTextField(onValueChange,focusRequesters[i], tl.size, cursorY)
                    coroutineScope.launch {
                        focusRequesters[i].requestFocus()
                    }
                }
            }
        }
    }

    if (expanded.value) {
        Popup(
            offset = with(density) {
                // textField의 위치와 cursorOffset을 사용하여 Popup 위치 조정
                IntOffset(
                    0,
                    cursorY.value.toInt() + 320
                )
            },
            onDismissRequest = { expanded.value = false }
        ) {
            DropMenuLazyRow(items, lastWord)
        }
    }
}

@Composable
fun ContentStartTextField(
    contentFieldValue: MutableState<TextFieldValue>,
    onValueChange: (TextFieldValue) -> Unit,
    cursorY: MutableState<Float>
) {
    BasicTextField(
        modifier = Modifier
            .border(0.5.dp, color = SpColor.Black)
            .fillMaxWidth(),
        value = contentFieldValue.value,
        onValueChange = onValueChange,
        onTextLayout = {
            // 텍스트가 변경될 때 커서의 y값을 계산
            cursorY.value = it.getCursorRect(contentFieldValue.value.selection.start).topLeft.y
        },
        textStyle = TextStyle(
            color = SpColor.Black,
            fontSize = 14.sp
        ),
        decorationBox = { innerTextField ->
            if (contentFieldValue.value.text.isEmpty()) {
                Text(
                    text = "입력..",
                    color = SpColor.StrokeGray,
                    fontSize = 14.sp
                )
            }
            innerTextField()
        },
    )
}

@Composable
fun ContentTextField(
    onValueChange: (TextFieldValue) -> Unit,
    focusRequesters: FocusRequester,
    listOffset: Int,
    cursorY: MutableState<Float>
) {

    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    SpLog.d("listOffset: ${listOffset}")
    SpLog.d("focusRequesters: ${focusRequesters}")
    SpLog.d("--------------------------------")
    BasicTextField(
        modifier = Modifier
            .border(0.5.dp, color = SpColor.Black)
            .fillMaxWidth()
            .focusRequester(focusRequesters),
        value = text,
        onValueChange = {
            onValueChange(text)
            text = it
        },
        onTextLayout = {
            cursorY.value = it.getCursorRect(text.selection.start).topLeft.y
        },
        textStyle = TextStyle(
            color = SpColor.Black,
            fontSize = 14.sp
        ),
    )
}

@Composable
fun DropMenuLazyRow(items: List<NewsAddMenuModel>, lastWord: MutableState<String>) {
    SpLog.d(lastWord)
    Box {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .shadow(5.dp, shape = RoundedCornerShape(12.dp), ambientColor = SpColor.BoxGray)
                .background(color = SpColor.White, shape = RoundedCornerShape(12.dp))
                .height(200.dp),
            contentPadding = PaddingValues(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            item {
                Text(
                    text = "명령어 목록",
                    color = SpColor.StrokeGray,
                    fontSize = 12.sp,
                )
            }
            items(items.size) {
                DropMenuItem(data = items[it])
            }
        }

    }
}

@Composable
fun DropMenuItem(data: NewsAddMenuModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                data.onClick()
            }
    ) {
        Row(
            modifier = Modifier
                .height(55.dp)
                .border(0.5.dp, color = SpColor.Void),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",
                modifier = Modifier.clip(shape = RoundedCornerShape(6.dp))
            )
            Column(
                modifier = Modifier.padding(start = 6.dp, top = 3.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = data.title,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = data.description,
                    color = SpColor.StrokeGray,
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun AlertScreen(alertScreenData: AlertScreenData) {
    val interactionSource = remember { MutableInteractionSource() }
    Dialog(onDismissRequest = { alertScreenData.onDismiss }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(20.dp)
        ) {
            Column {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    text = alertScreenData.title,
                    fontWeight = FontWeight.Bold,
                    color = SpColor.Black
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp,
                    text = alertScreenData.message,
                    color = SpColor.Black
                )

                Spacer(modifier = Modifier.height(24.dp))
                Row {
                    Text(
                        text = alertScreenData.confirmText,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) { alertScreenData.onConfirm() }
                            .weight(1f)
                            .background(
                                color = SpColor.Theme,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(vertical = 14.dp)
                    )

                    if (alertScreenData.dismissText.isNotEmpty()) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = alertScreenData.dismissText,
                            textAlign = TextAlign.Center,
                            color = SpColor.StrokeGray,
                            modifier = Modifier
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null
                                ) { alertScreenData.onDismiss() }
                                .weight(1f)
                                .background(
                                    shape = RoundedCornerShape(12.dp),
                                    color = SpColor.Unable,
                                )
                                .padding(vertical = 14.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GeneratedTitleText(titleFieldValue: String, isLoading: Boolean) {
    Text(
        text = titleFieldValue,
        style = TextStyle(
            fontSize = 14.sp,
            color = SpColor.Theme,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier
            .padding(top = 8.dp, start = 15.dp, end = 15.dp)
            .background(
                brush = shimmerLoading(isLoading),
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 0.5.dp,
                brush = Brush.horizontalGradient(
                    listOf(
                        SpColor.ThemeStart,
                        SpColor.ThemeEnd
                    )
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .fillMaxWidth()
            .padding(15.dp)
    )
}

@Composable
fun CloseBox(onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }

    Image(
        modifier = Modifier
            .height(25.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick()
            },
        painter = painterResource(id = R.drawable.ic_close),
        contentDescription = "",
    )
}

@Composable
fun SendNewsCheckBox(isSend: Boolean, isTitleInit: Boolean, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .height(25.dp)
            .width(25.dp)
    ) {
        if (isSend) {
            CircularProgressIndicator(
                strokeWidth = 1.dp,
                color = SpColor.Theme,
                modifier = Modifier.padding(3.dp)
            )
        } else {
            val checkAlpha = if (isTitleInit) 1f else 0.3f

            Image(
                modifier = Modifier
                    .alpha(checkAlpha)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        onClick()
                    },
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = "",
            )
        }
    }
}

@Composable
fun GenerateTitleButton(
    modifier: Modifier,
    isVisible: Boolean,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    val textEndPadding = if (isVisible) 0.dp else 15.dp
    val loadingTextColor = if (isLoading) SpColor.StrokeGray else SpColor.Black
    val loadingText = if (isLoading) "생성 중" else "제목 생성하기"

    val interactionSource = remember { MutableInteractionSource() }

    Text(
        text = loadingText,
        style = TextStyle(
            fontSize = 14.sp,
            color = loadingTextColor,
            textAlign = TextAlign.Center
        ),
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick()
            }
            .padding(start = 15.dp, end = textEndPadding)
            .background(
                color = SpColor.BoxGray,
                shape = RoundedCornerShape(12.dp)
            )
            .fillMaxWidth()
            .padding(9.dp)
    )
}

@Composable
fun TitleReport(onClick: () -> Unit) {
    Text(
        text = "제목 신고",
        style = TextStyle(
            fontSize = 14.sp,
            color = SpColor.Black,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier
            .padding(start = 8.dp, end = 15.dp)
            .background(color = SpColor.BoxGray, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 25.dp, vertical = 9.dp)
            .clickable {
                onClick()
            }
    )
}

@Preview
@Composable
fun DropMenuLazyRowPreview() {
    val items = listOf(
        NewsAddMenuModel(
            title = "이미지",
            description = "파일을 업로드하여 추가하세요",
            image = 0
        ) {},
        NewsAddMenuModel(
            title = "AI 자동 생성",
            description = "AI를 통해 뉴스를 완성하세요",
            image = 0
        ) {},
        NewsAddMenuModel(
            title = "소제목",
            description = "섹션 제목 설정",
            image = 0
        ) {}
    )
    DropMenuLazyRow(items, remember { mutableStateOf("") })
}
