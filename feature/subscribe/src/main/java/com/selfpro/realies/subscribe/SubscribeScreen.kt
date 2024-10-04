package com.selfpro.realies.subscribe

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.selfpro.network.request.RealiesRequest
import com.selfpro.realies.common.NewsShimmerColumnItem
import com.selfpro.realies.data.model.LoadState
import com.selfpro.realies.feature.assests.news.NewsColumnLazyItem
import com.selfpro.realies.shared.NetworkingViewModel
import com.selfpro.realies.util.common.SpColor
import com.selfpro.realies.util.common.SpLog
import com.selfpro.realies.util.icon.IcRealieslogo
import com.selfpro.realies.util.icon.SpIcon

@Composable
fun SubscribeScreen(
    navHostController: NavHostController,
    networkingViewModel: NetworkingViewModel = hiltViewModel()
) {
    SpLog.d("SubscribeScreen")

//    val newsState by networkingViewModel.subscribeFlow.collectAsState()
//    networkingViewModel.getRecommendationRealies(0)

    var selectedIndex by remember { mutableStateOf(-1) }
    val interactionSource = remember { MutableInteractionSource() }

    val items = listOf("test", "hello", "world", "mr", "my", "yesterday")

    LazyColumn(modifier = Modifier.background(color = SpColor.White)) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Icon(
                    imageVector = SpIcon.IcRealieslogo,
                    contentDescription = "RealiesLogo",
                    modifier = Modifier.padding(bottom = 10.dp, start = 15.dp)
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 7.dp),
                ) {
                    items(items.size) { index ->
                        val isSelected = selectedIndex == index
                        val isAnySelected = selectedIndex != -1

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null
                                ) {
                                    selectedIndex = if (isSelected) -1 else index
                                }
                                .graphicsLayer(alpha = if (isAnySelected && !isSelected) 0.3f else 1f)
                                .background(if (isSelected) Color.LightGray else Color.Transparent)
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SubscribeLatestRowItem()
                        }
                    }
                }

                var subscribed by remember { mutableStateOf(true) }

                val backgroundColor = if (subscribed) SpColor.BoxGray else SpColor.StrokeGray
                val textColor = if (subscribed) SpColor.StrokeGray else SpColor.BoxGray
                val text = if (subscribed) "구독 중" else "구독"

                HorizontalDivider(
                    thickness = 0.5.dp,
                    color = Color(0xFFE7E7E7),
                    modifier = Modifier.padding(
                        top = 7.dp,
                        bottom = 15.dp
                    )
                )
                Box(
                    modifier = Modifier
                        .padding(end = 15.dp, bottom = 15.dp)
                        .align(Alignment.End)
                        .background(
                            color = backgroundColor,
                            shape = RoundedCornerShape(100.dp)
                        )
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            subscribed = !subscribed
                        }
                        .border(
                            width = 0.5.dp,
                            color = SpColor.StrokeGray,
                            shape = RoundedCornerShape(100.dp)
                        )
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = text,
                        color = textColor,
                        fontSize = 12.sp
                    )
                }
            }
        }

//        when (newsState) {
//            is LoadState.Loading -> {
//                items(20) {
//                    NewsShimmerColumnItem()
//
//                    HorizontalDivider(
//                        thickness = 0.5.dp,
//                        color = SpColor.StrokeGray,
//                        modifier = Modifier.padding(
//                            horizontal = 15.dp,
//                            vertical = 8.dp
//                        )
//                    )
//                }
//            }
//
//            is LoadState.Success -> {
//                @Suppress("UNCHECKED_CAST")
//                val data = (newsState as LoadState.Success).data as List<RealiesRequest>
//
//                items(data.size) {
//                    NewsColumnLazyItem(modifier = Modifier, data = data[it], onClick = {
////                        sharedViewModel.mainNavController.navigate("news")
//                    })
//                    HorizontalDivider(
//                        thickness = 0.5.dp,
//                        color = SpColor.StrokeGray,
//                        modifier = Modifier.padding(
//                            horizontal = 15.dp,
//                            vertical = 8.dp
//                        )
//                    )
//                }
//            }
//
//            is LoadState.Error -> {
//
//            }
//        }
    }
}

@Composable
fun SubscribeLatestRowItem() {
    val interactionSource = remember { MutableInteractionSource() }

    var stokeColor = remember { mutableStateOf(SpColor.Transparent) }

    val data = "test"

    Column(
        modifier = Modifier
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
            .width(90.dp)
            .height(120.dp)
            .border(3.dp, stokeColor.value, shape = RoundedCornerShape(10.dp))
            .background(
                color = SpColor.White,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
//                .background(
//                    shimmerEffect(),
//                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
//                )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
                .padding(5.dp)
        ) {
            Text(text = data, color = SpColor.Black, fontSize = 10.sp)
        }
    }
}
