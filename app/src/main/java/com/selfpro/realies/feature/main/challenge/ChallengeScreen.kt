package com.selfpro.realies.feature.main.challenge

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.selfpro.realies.R
import com.selfpro.realies.SharedViewModel
import com.selfpro.realies.data.model.LoadState
import com.selfpro.realies.data.network.request.RealiesRequest
import com.selfpro.realies.feature.assests.news.CategoryItem
import com.selfpro.realies.feature.assests.news.CategoryRealiesItem
import com.selfpro.realies.feature.assests.news.NewsColumnLazyItem
import com.selfpro.realies.feature.assests.news.NewsShimmerColumnItem
import com.selfpro.realies.feature.assests.news.RealiesSearchBox
import com.selfpro.realies.feature.main.realies.RealiesViewModel
import com.selfpro.realies.ui.color.SpColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChallengeScreen(
    sharedViewModel: SharedViewModel,
    realiesViewModel: RealiesViewModel = viewModel()
) {

    val categoryScrollState = rememberScrollState()

    val newsState by realiesViewModel.realeiesFlow.collectAsState()
    realiesViewModel.getRecommendationRealies(0)

    LazyColumn {
        item {
            Row {

                Image(
                    painter = painterResource(id = R.drawable.bg_realies),
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
                    NewsColumnLazyItem(modifier = Modifier, data = data[it], onClick = {
                        sharedViewModel.url = it
                        sharedViewModel.mainNavController.navigate("news")
                    })

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

@Preview
@Composable
fun ChallengeScreenPreview() {
    ChallengeScreen(sharedViewModel = SharedViewModel())
}