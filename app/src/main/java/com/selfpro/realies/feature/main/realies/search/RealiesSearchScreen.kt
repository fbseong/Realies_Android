package com.selfpro.realies.feature.main.realies.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
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
import com.selfpro.realies.R
import com.selfpro.realies.feature.main.realies.CategoryItem
import com.selfpro.realies.ui.color.SpColor
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen() {
    var searchDetail by remember { mutableStateOf(true) }

    LazyColumn {
        item {
            Image(
                painter = painterResource(id = R.drawable.bg_realies),
                contentDescription = "RealiesLogo",
                modifier = Modifier.padding(bottom = 10.dp, start = 15.dp)
            )
            SearchBox(
                onSearch = {},
                onTextChanged = {
                    searchDetail = it
                })

            if (searchDetail) {
                FlowRowRecentCategroy(
                    "최근 검색어", listOf(
                        "안녕",
                        "Helloewfewfwe",
                        "World!wefewf",
                        "Heefwfewfllo",
                        "Worlewfewfd!",
                        "Hfewfwefello",
                    )
                )
                FlowRowRecentCategroy(
                    "인기 검색어", listOf(
                        "안녕",
                        "Helloewfewfwe",
                        "World!wefewf",
                        "Heefwfewfllo",
                        "Worlewfewfd!",
                        "Hfewfwefello",
                    )
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBox(onSearch: (String) -> Unit, onTextChanged: (Boolean) -> Unit) {
    var text by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        delay(300)
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Column {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onTextChanged(it.isBlank())
            },
            modifier = Modifier
                .focusRequester(focusRequester)
                .padding(horizontal = 15.dp)
                .fillMaxWidth()
                .border(
                    color = SpColor.StrokeGray,
                    shape = RoundedCornerShape(100.dp),
                    width = 0.5.dp
                )
                .background(
                    color = SpColor.BoxGray,
                    shape = RoundedCornerShape(100.dp)
                )
                .padding(12.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search // 키보드에 검색 버튼을 표시
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide() // 키보드 숨김
                }
            ),
            textStyle = TextStyle(
                color = SpColor.Black,
                fontSize = 14.sp
            ),
            decorationBox = { innerTextField ->
                if (text.isEmpty()) {
                    Text(
                        text = "검색..",
                        fontSize = 14.sp,
                        color = SpColor.StrokeGray
                    )
                }
                innerTextField()
            }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRowRecentCategroy(title: String, data: List<String>) {
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
            repeat(data.size) {
                CategoryItem(data[it])
            }

        }
    }
}