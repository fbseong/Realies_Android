package com.selfpro.realies.feature.main.realies.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.selfpro.realies.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChallengeSearchScreen() {
    var searchDetail by remember { mutableStateOf(true) }

//    LazyColumn {
//        item {
//            Image(
//                painter = painterResource(id = R.drawable.bg_realies),
//                contentDescription = "RealiesLogo",
//                modifier = Modifier.padding(bottom = 10.dp, start = 15.dp)
//            )
//            SearchBox(
//                onSearch = {},
//                onTextChanged = {
//                    searchDetail = it
//                })
//
//            if (searchDetail) {
//                FlowRowRecentCategroy(
//                    "최근 검색어", listOf(
//                        "안녕",
//                        "Helloewfewfwe",
//                        "World!wefewf",
//                        "Heefwfewfllo",
//                        "Worlewfewfd!",
//                        "Hfewfwefello",
//                    )
//                )
//                FlowRowRecentCategroy(
//                    "인기 검색어", listOf(
//                        "안녕",
//                        "Helloewfewfwe",
//                        "World!wefewf",
//                        "Heefwfewfllo",
//                        "Worlewfewfd!",
//                        "Hfewfwefello",
//                    )
//                )
//            }
//        }
//    }
}