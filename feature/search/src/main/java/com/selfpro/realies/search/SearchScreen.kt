package com.selfpro.realies.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.selfpro.realies.util.common.SpColor
import com.selfpro.realies.util.common.SpLog
import com.selfpro.realies.util.icon.IcRealieslogo
import com.selfpro.realies.util.icon.SpIcon
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(navHostController: NavHostController) {
    var searchDetail by remember { mutableStateOf(true) }
    var textState by remember { mutableStateOf("") }
    var cursorPosition = remember { mutableStateOf(0) }

    val recentCategory = listOf(
        "비투비", "육성재", "이창섭", "비투", "나의 바람", "루시"
    )
    val popularCategory = listOf(
        "비투비", "루시", "뜨거워지자", "육성재", "이창섭"
    )

    LazyColumn {
        item {
            Icon(
                imageVector = SpIcon.IcRealieslogo,
                contentDescription = "RealiesLogo",
                modifier = Modifier.padding(bottom = 10.dp, start = 15.dp)
            )
            SearchBox(
                onSearch = { SpLog.d(it) },
                text = textState,
                onTextChange = { textState = it },
                cursorPosition = cursorPosition
            )
            LaunchedEffect(textState) {
                searchDetail = textState.isEmpty()
            }

            if (searchDetail) {
                FlowRowRecentCategroy("최근 검색어", recentCategory) {
                    cursorPosition.value = it.length
                    textState = it
                }
                FlowRowRecentCategroy("인기 검색어", popularCategory) {
                    cursorPosition.value = it.length
                    textState = it
                }
            }
        }
    }
}

@Composable
fun SearchBox(
    onSearch: (String) -> Unit,
    text: String,
    cursorPosition: MutableState<Int>,
    onTextChange: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        delay(300)
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Box(
        Modifier
            .padding(horizontal = 15.dp)
            .height(44.dp)
            .border(
                color = SpColor.StrokeGray,
                shape = RoundedCornerShape(100.dp),
                width = 0.5.dp
            )
            .background(
                color = SpColor.BoxGray,
                shape = RoundedCornerShape(100.dp)
            )
    ) {
        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier
                .padding(start = 12.dp)
                .align(Alignment.Center)
                .focusRequester(focusRequester)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search // 키보드에 검색 버튼을 표시
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide() // 키보드 숨김
                    onSearch(text)
                }
            ),
            textStyle = TextStyle(
                color = SpColor.Black,
                fontSize = 14.sp
            ),
        )
        if (text.isEmpty()) {
            Box(modifier = Modifier.height(44.dp)) {
                Text(
                    text = "검색..",
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                    color = SpColor.StrokeGray
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRowRecentCategroy(title: String, data: List<String>, onClick: (String) -> Unit) {
    Column(modifier = Modifier.padding(15.dp)) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 15.dp)
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(data.size) { index ->
                SearchItem(text = data[index]) {
                    onClick(it)
                }
            }
        }
    }
}

@Composable
fun SearchItem(text: String, onClick: (String) -> Unit) {

    val interactionSource = remember { MutableInteractionSource() }

    Text(
        text = text,
        color = SpColor.Black,
        fontSize = 12.sp,
        modifier = Modifier
            .border(
                width = 0.5.dp,
                color = SpColor.StrokeGray,
                shape = RoundedCornerShape(100.dp)
            )
            .clickable(
                onClick = { onClick(text) },
                interactionSource = interactionSource,
                indication = null
            )
            .background(color = SpColor.BoxGray, shape = RoundedCornerShape(100.dp))
            .padding(horizontal = 20.dp, vertical = 8.dp)
    )
}