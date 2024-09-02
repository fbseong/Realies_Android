package com.selfpro.realies.feature.main.realies

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import com.selfpro.realies.R
import com.selfpro.realies.SharedViewModel
import com.selfpro.realies.data.model.LoadState
import com.selfpro.realies.data.network.request.RealiesRequest
import com.selfpro.realies.feature.lazy.NewsThumbViewModel
import com.selfpro.realies.ui.color.SpColor
import com.selfpro.realies.util.shimmerEffect.ShimmerNewsDateBox
import com.selfpro.realies.util.shimmerEffect.ShimmerNewsTitleBox
import com.selfpro.realies.util.shimmerEffect.ShimmerNewsWithdrawnLogo
import com.selfpro.realies.util.shimmerEffect.shimmerEffect
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RealiesScreen(
    sharedViewModel: SharedViewModel,
    realiesViewModel: NewsThumbViewModel = viewModel()
) {

    val categoryScrollState = rememberScrollState()

    val newsState by realiesViewModel.realeiesFlow.collectAsState()
    realiesViewModel.getRecommendationRealies(0)


    LazyColumn {
        item {
            Image(
                painter = painterResource(id = R.drawable.bg_realies),
                contentDescription = "RealiesLogo",
                modifier = Modifier.padding(bottom = 10.dp, start = 15.dp)
            )
            RealiesSearchBox {
                sharedViewModel.mainNavController.navigate("realiesSearch")
            }


        }
        stickyHeader {
            val items = listOf("Realies", "hello", "world", "bravo", "my", "life")
            var selectedItem by remember { mutableStateOf<String?>(null) }
            var selectedRealies by remember { mutableStateOf(false) }

            LazyRow(
                modifier = Modifier.padding(vertical = 8.dp),
                contentPadding = PaddingValues(horizontal = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items.size) {
                    val item = items[it]
                    val isSelected = item == selectedItem

                    if (it == 0) {
                        CategoryRealiesItem(categoryInfo = "릴리즈", isSelected = selectedRealies) {
                            selectedRealies = !selectedRealies
                        }

                    } else {
                        CategoryItem(categoryInfo = "hello", isSelected = isSelected) {
                            selectedItem = if (selectedItem == item) {
                                null
                            } else {
                                item
                            }
                        }
                    }
                }
            }
        }
        when (newsState) {
            is LoadState.Loading -> {
                items(20) {
                    NewsShimmerColumnItem()

                    HorizontalDivider(
                        thickness = 0.5.dp,
                        color = SpColor.StrokeGray,
                        modifier = Modifier.padding(
                            horizontal = 15.dp,
                            vertical = 8.dp
                        )
                    )
                }
            }

            is LoadState.Success -> {
                @Suppress("UNCHECKED_CAST")
                val data = (newsState as LoadState.Success).data as List<RealiesRequest>

                items(data.size) {
                    NewsThumbModelColumnItem(modifier = Modifier, data = data[it]) {
                        sharedViewModel.url = it
                        sharedViewModel.mainNavController.navigate("news")
                    }

                    HorizontalDivider(
                        thickness = 0.5.dp,
                        color = SpColor.StrokeGray,
                        modifier = Modifier.padding(
                            horizontal = 15.dp,
                            vertical = 8.dp
                        )
                    )
                }
            }

            is LoadState.Error -> {

            }
        }

    }
}


@Composable
fun CategoryItem(categoryInfo: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) SpColor.StrokeGray else SpColor.BoxGray
    val textColor = if (isSelected) SpColor.BoxGray else SpColor.StrokeGray

    val interactionSource = remember { MutableInteractionSource() }

    Text(
        text = categoryInfo,
        color = textColor,
        fontSize = 12.sp,
        modifier = Modifier
            .border(
                width = 0.5.dp,
                color = SpColor.StrokeGray,
                shape = RoundedCornerShape(100.dp)
            )
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null
            )
            .background(color = backgroundColor, shape = RoundedCornerShape(100.dp))
            .padding(horizontal = 20.dp, vertical = 8.dp)
    )
}

@Composable
fun CategoryRealiesItem(categoryInfo: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundBrush = if (isSelected) Brush.horizontalGradient(
        listOf(
            Color(0xFFFFB054),
            Color(0xFFFF6EA2)
        )
    ) else Brush.horizontalGradient(
        listOf(
            SpColor.BoxGray,
            SpColor.BoxGray
        )
    )

    val textColor = if (isSelected) SpColor.White else SpColor.Black

    val interactionSource = remember { MutableInteractionSource() }

    Text(
        text = categoryInfo,
        color = textColor,
        fontSize = 12.sp,
        modifier = Modifier
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null
            )
            .border(
                brush = Brush.horizontalGradient(listOf(Color(0xFFFFB054), Color(0xFFFF6EA2))),
                width = 0.5.dp,
                shape = RoundedCornerShape(100.dp)
            )
            .background(brush = backgroundBrush, shape = RoundedCornerShape(100.dp))
            .padding(horizontal = 20.dp, vertical = 8.dp)
    )
}

@Composable
fun RealiesSearchBox(onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .height(44.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
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
        Text(
            text = "검색..",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp)
                .align(Alignment.Center),
            color = SpColor.StrokeGray
        )
    }

//    BasicTextField(
//        value = text,
//        enabled = false,
//        onValueChange = { text = it },
//        modifier = modifier
//            .clickable(
//                interactionSource = interactionSource,
//                indication = null
//            ) {
//                onClick()
//            }
//            .fillMaxWidth()
//            .border(
//                color = SpColor.StrokeGray,
//                shape = RoundedCornerShape(100.dp),
//                width = 0.5.dp
//            )
//            .background(
//                color = SpColor.BoxGray,
//                shape = RoundedCornerShape(100.dp)
//            )
//            .padding(12.dp),
//
//        textStyle = TextStyle(
//            color = SpColor.Black,
//            fontSize = 14.sp
//        ),
//        decorationBox = { innerTextField ->
//            if (text.isEmpty()) {
//                Text(
//                    text = "검색..",
//                    fontSize = 14.sp,
//                    color = SpColor.StrokeGray
//                )
//            }
//            innerTextField()
//        }
//    )
}


@Composable
fun NewsThumbModelColumnItem(
    modifier: Modifier = Modifier,
    data: RealiesRequest,
    onClick: (String?) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(modifier = modifier.padding(horizontal = 15.dp)) {

        Box(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onClick(data.url)
                }
                .clip(RoundedCornerShape(12.dp)),
        ) {
            SubcomposeAsyncImage(
                model = (data.images?.get(0) ?: ""),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentScale = ContentScale.FillWidth,
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                            .background(shimmerEffect())
                    )
                },
                error = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                            .background(shimmerEffect())
                    )
                }
            )
        }

        Image(
            modifier = Modifier
                .padding(bottom = 3.dp)
                .height(14.dp),
            painter = painterResource(id = R.drawable.bg_realies),
            contentDescription = "News Withdrawn Logo"
        )

        NewsTitleBox(modifier = Modifier.padding(bottom = 1.dp), data = data)

        NewsDateBox(data = data)
    }
}

@Composable
fun NewsShimmerColumnItem(
) {
    Column(modifier = Modifier.padding(horizontal = 15.dp)) {
        Box(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .clip(RoundedCornerShape(12.dp)),
        ) {
            Box(
                modifier = Modifier
                    .background(shimmerEffect())
                    .height(240.dp)
                    .fillMaxWidth()
            )
        }
        ShimmerNewsWithdrawnLogo()
        ShimmerNewsTitleBox()
        ShimmerNewsDateBox()
    }
}


@Composable
fun NewsTitleBox(modifier: Modifier, data: RealiesRequest) {
    Text(
        modifier = modifier,
        text = data.title,
        fontSize = 14.sp,
        textAlign = TextAlign.Left,
        color = Color.Black
    )
}

@Composable
fun NewsDateBox(
    data: RealiesRequest,
) {
    Text(
        text = getDurationTime(data.publishedAt),
        fontSize = 12.sp,
        textAlign = TextAlign.Left,
        color = Color.Gray
    )
}

private fun getDurationTime(createdAt: String): String {
    val postCreatedAt = LocalDateTime.parse(
        createdAt, DateTimeFormatter.ISO_DATE_TIME
    )
    val duration = Duration.between(postCreatedAt, LocalDateTime.now())

    val minutes = duration.toMinutes()
    val hours = duration.toHours()
    val days = duration.toDays()
    val weeks = duration.toDays() / 7
    val years = duration.toDays() / 365

    return when {
        minutes < 1 -> "방금 전"
        minutes < 60 -> "${minutes}분 전"
        hours < 24 -> "${hours}시간 전"
        days < 7 -> "${days}일 전"
        weeks < 1 -> "${days}일 전"
        weeks < 5 -> "${weeks}주 전"
        years < 1 -> "${days / 30}개월 전"
        else -> "${years}년 전"
    }
}

@Preview
@Composable
fun CategoryRealiesItemPreview() {
//    CategoryRealiesItem("Realies")

}