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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.selfpro.network.request.RealiesRequest
import com.selfpro.realies.common.NewsLazyColumn.NewsLazyColumItemViewModel
import com.selfpro.realies.common.ShimmerNewsTitleBox
import com.selfpro.realies.common.shimmerEffect
import com.selfpro.realies.data.model.LoadState
import com.selfpro.realies.util.common.SpColor
import com.selfpro.realies.util.common.SpLog
import com.selfpro.realies.util.icon.IcRealieslogo
import com.selfpro.realies.util.icon.SpIcon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.orbitmvi.orbit.compose.collectAsState
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Preview
@Composable
fun NewsLazyColumnItemPreview() {
    Box(modifier = Modifier.background(color = SpColor.White)) {
    }
}

@Composable
fun NewsLazyColumnItem(
    modifier: Modifier = Modifier,
    data: RealiesRequest,
    onClick: (String?) -> Unit,
    viewModel: NewsLazyColumItemViewModel = hiltViewModel()
) {

    val itemState by viewModel.collectAsState()

    val interactionSource = remember { MutableInteractionSource() }
    var launched by rememberSaveable { mutableStateOf(false) }

    if (!launched) {
        viewModel.generateNewsTitle(data.content)
        launched = true
    }

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
        Row(modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 15.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                if (itemState.isLoading) {
                    ShimmerNewsTitleBox()
                } else {
                    NewsTitleBox(
                        modifier = Modifier
                            .padding(bottom = 3.dp),
                        fontSize = 15.sp,
                        data = itemState.generatedNewsTitle
                    )
                }
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
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(data.images?.get(0) ?: "")
            .diskCachePolicy(CachePolicy.ENABLED)
            .build(),
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
    data: String,
) {
    Text(
        modifier = modifier,
        text = data.replace("## ", ""),
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
) {
}
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
        NewsTitleBox(modifier = Modifier.weight(1f), fontSize = 14.sp, data = data.title)
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
