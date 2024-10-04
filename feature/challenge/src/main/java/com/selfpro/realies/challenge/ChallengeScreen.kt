package com.selfpro.realies.challenge

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.selfpro.network.request.RealiesRequest
import com.selfpro.realies.common.CategoryItem
import com.selfpro.realies.common.RealiesSearchBox
import com.selfpro.realies.feature.assests.news.NewsImageBox
import com.selfpro.realies.shared.NetworkingViewModel
import com.selfpro.realies.util.common.SpColor
import com.selfpro.realies.util.icon.IcRealieslogo
import com.selfpro.realies.util.icon.SpIcon

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChallengeScreen(
    navHostController: NavHostController,
    netWorkViewModel: NetworkingViewModel = hiltViewModel()
) {


//    val newsState by netWorkViewModel.challengeFlow.collectAsState()
//    netWorkViewModel.getRecommendationRealies(0)

    LazyColumn(modifier = Modifier.background(color = SpColor.White)) {
        item {
            Row {

                Image(
                    imageVector = SpIcon.IcRealieslogo,
                    contentDescription = "RealiesLogo",
                    modifier = Modifier.padding(bottom = 10.dp, start = 15.dp)
                )
                Text(
                    text = "도전 뉴스",
                    color = SpColor.Theme,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(bottom = 8.dp)
                )
            }
            RealiesSearchBox {
                navHostController.navigate("realiesSearch")
            }
        }
        stickyHeader {
            val items = listOf("엔터테인먼트", "테크", "연애","스포츠","경제")
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

                    CategoryItem(categoryInfo = items[it], isSelected = isSelected) {
                        selectedItem = if (selectedItem == item) {
                            null
                        } else {
                            item
                        }
                    }

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

        val data =
            listOf(
                RealiesRequest("3개월 제작 Realies 출시하자 마자 파산위기", "d", "1시간 전", listOf("")),
                RealiesRequest("육성재 Be somebody 발매", "d", "3시간 전", listOf("https://img.hankyung.com/photo/201609/02.12440532.1.jpg")),
                RealiesRequest("1년 끝에 오뚜기 라면 업그레이드", "d", "10분 전", listOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTzbOL2bGA17niNRaETpxTl1CTNo9LfQcwVdQ&s"))
            )

        items(data.size) {
            OlumnLazyItem(modifier = Modifier, data = data[it])

            HorizontalDivider(
                thickness = 0.5.dp,
                color = SpColor.StrokeGray,
                modifier = Modifier.padding(
                    horizontal = 15.dp,
                    vertical = 8.dp
                )
            )
        }
//            }

//            is LoadState.Error -> {
//
//            }
//        }

    }
}

@Composable
fun OlumnLazyItem(
    modifier: Modifier = Modifier,
    data: RealiesRequest,
) {

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .padding(bottom = 8.dp)
                .clip(RoundedCornerShape(12.dp)),
        ) {
            NewsImageBox(data = data)
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(bottom = 5.dp)
        ) {
            Image(
                modifier = Modifier
                    .height(14.dp),
                imageVector = SpIcon.IcRealieslogo,
                contentDescription = "News Withdrawn Logo"
            )
        }

        Row(modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 15.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    modifier = modifier.padding(bottom = 3.dp),
                    text = data.title,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Left,
                    color = Color.Black
                )
                Text(
                    modifier = modifier,
                    text = data.publishedAt,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Left,
                    color = Color.Gray
                )
            }
            Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)
        }
    }
}