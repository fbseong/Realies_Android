package com.selfpro.realies.feature.assests.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.selfpro.network.request.RealiesRequest
import com.selfpro.realies.common.shimmerEffect
import com.selfpro.realies.data.model.LoadState
import com.selfpro.realies.util.common.SpColor
import com.selfpro.realies.util.icon.IcRealieslogo
import com.selfpro.realies.util.icon.SpIcon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Preview
@Composable
fun NewsCoulmnLazyItemPreview() {
    Box(modifier = Modifier.background(color = SpColor.White)) {
        NewsColumnLazyItem(
            data = RealiesRequest(
                title = "Realies 출시하자마자 파산 위기... 문슨일?",
                "ㅈㅁㄷㄹㅈㅁㄷㄹ",
                publishedAt = "wefwaefafwea",
                images = null
            )
        ) {}
    }
}

@Composable
fun NewsColumnLazyItem(
    modifier: Modifier = Modifier,
    data: RealiesRequest,
    onClick: (String?) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .padding(bottom = 8.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) { onClick(data.url) }
                .clip(RoundedCornerShape(12.dp)),
        ) {
            NewsImageBox(data = data)
        }

        NewsProviderBox(data = data)
        Row(modifier = Modifier.padding(start = 15.dp, end = 15.dp,bottom = 15.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                NewsTitleBox(
                    modifier = Modifier
                        .padding(bottom = 3.dp),
                    fontSize = 15.sp,
                    data = data
                )
                NewsDateBox(modifier = Modifier.padding(), data = data)
            }
            Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)
        }
        NewsProviderLazyRow(data = data)
    }
}

@Composable
fun NewsImageBox(data: RealiesRequest) {
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
        error = {}
    )
}

@Composable
fun NewsProviderBox(data: RealiesRequest) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(bottom = 5.dp)
    ) {
        if (data.providerImage != null || data.provider != null) {
            if (data.providerImage != null) {
                SubcomposeAsyncImage(
                    model = (data.providerImage),
                    contentDescription = null,
                    modifier = Modifier
                        .height(14.dp),
                    contentScale = ContentScale.FillHeight,
                    loading = {
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(14.dp)
                                .background(shimmerEffect())
                        )
                    }
                )
            }
            Text(
                text = data.provider ?: "",
                fontSize = 12.sp,
                color = SpColor.StrokeGray,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        } else {
            Image(
                modifier = Modifier
                    .height(14.dp),
                imageVector = SpIcon.IcRealieslogo,
                contentDescription = "News Withdrawn Logo"
            )
        }

    }
}

@Composable
fun NewsTitleBox(
    modifier: Modifier = Modifier,
    fontSize: TextUnit,
    data: RealiesRequest,
) {
    var newsTitle by remember { mutableStateOf(data.title) }

    val _loadStateFlow = MutableStateFlow<LoadState>(LoadState.Loading)
    val loadStateFlow = _loadStateFlow.asStateFlow()

    if (data.content.isNotEmpty()) {
//        realiesViewModel.getNewsTitle(_loadStateFlow, data.content)

        LaunchedEffect(Unit) {
            loadStateFlow.collect { state ->
                when (state) {
                    is LoadState.Success -> {
                        newsTitle = (state.data as String).replace("## ", "").replace("\n", "")
                    }

                    is LoadState.Loading -> {}
                    is LoadState.Error -> {}
                }
            }
        }
    }

    Text(
        modifier = modifier,
        text = newsTitle,
        fontSize = fontSize,
        textAlign = TextAlign.Left,
        color = Color.Black
    )
}

@Composable
fun NewsDateBox(
    modifier: Modifier = Modifier,
    data: RealiesRequest,
) {
    Text(
        modifier = modifier,
        text = getDurationTime(data.publishedAt),
        fontSize = 12.sp,
        textAlign = TextAlign.Left,
        color = Color.Gray
    )
}

@Composable
fun NewsProviderLazyRow(
    data: RealiesRequest,
){}
//    viewModel: RealiesViewModel = hiltViewModel()
//) {
//    if (!data.provider.isNullOrEmpty()) {
//        val loadState = remember { mutableStateOf<LoadState>(LoadState.Loading) }
//        var providerData by remember { mutableStateOf(listOf<RealiesRequest>()) }
//
//        viewModel.getProviderRealies(provider = data.provider, loadStateFlow = loadState)
//
//        LazyRow(
//            horizontalArrangement = Arrangement.spacedBy(8.dp),
//            contentPadding = PaddingValues(horizontal = 15.dp)
//        ) {
//            items(providerData.size) {
//                NewsProviderLazyRowItem(data = providerData[it])
//            }
//        }
//
//        when (loadState.value) {
//            is LoadState.Success -> {
//                providerData =
//                    ((loadState.value as LoadState.Success).data as List<RealiesRequest>).drop(1)
//            }
//
//            is LoadState.Loading -> {}
//            is LoadState.Error -> {}
//        }
//    }
//}

@Composable
fun NewsProviderLazyRowItem(data: RealiesRequest) {
    Column(
        modifier = Modifier
            .width(260.dp)
            .height(90.dp)
            .background(shape = RoundedCornerShape(12.dp), color = SpColor.BoxGray)
            .padding(15.dp)
    ) {
        NewsTitleBox(modifier = Modifier.weight(1f), fontSize = 14.sp, data = data)
        NewsDateBox(data = data)
    }
}

private fun getDurationTime(createdAt: String): String {
    // ISO 8601 포맷과 "yyyy-MM-dd HH:mm:ss" 포맷을 처리하기 위한 포맷터
    val isoFormatter = DateTimeFormatter.ISO_DATE_TIME
    val customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    // 입력 문자열의 형식에 따라 포맷터 선택
    val postCreatedAt = try {
        if (createdAt.contains("T")) {
            LocalDateTime.parse(createdAt, isoFormatter)
        } else {
            LocalDateTime.parse(createdAt, customFormatter)
        }
    } catch (e: Exception) {
        return "잘못된 날짜 형식"
    }

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
