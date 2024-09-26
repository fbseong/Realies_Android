package com.selfpro.realies.feature.main.realies

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.selfpro.realies.R
import com.selfpro.realies.SharedViewModel
import com.selfpro.realies.data.model.LoadState
import com.selfpro.realies.data.network.request.RealiesRequest
import com.selfpro.realies.feature.assests.news.NewsCategory
import com.selfpro.realies.feature.assests.news.NewsColumnLazyItem
import com.selfpro.realies.feature.assests.news.NewsShimmerColumnItem
import com.selfpro.realies.feature.assests.news.RealiesSearchBox
import com.selfpro.realies.ui.color.SpColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RealiesScreen(
    sharedViewModel: SharedViewModel = hiltViewModel(),
    realiesViewModel: RealiesViewModel = hiltViewModel()
) {
    val categoryScrollState = rememberScrollState()

    val newsState by realiesViewModel.realeiesFlow.collectAsState()
    realiesViewModel.getRecommendationRealies(0)

    LazyColumn {
        item {
            Image(
                painter = painterResource(id = R.drawable.bg_realies),
                contentDescription = "RealiesLogo",
                modifier = Modifier.padding(bottom = 10.dp, start = 15.dp, top = 10.dp)
            )
            RealiesSearchBox {
                sharedViewModel.mainNavController.navigate("realiesSearch")
            }
        }
        stickyHeader {
            NewsCategory()
        }
        when (newsState) {
            is LoadState.Loading -> {
                items(5) {
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
            is LoadState.Error -> {}
        }
    }
}
