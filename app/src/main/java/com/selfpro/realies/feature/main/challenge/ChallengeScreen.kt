package com.selfpro.realies.feature.main.challenge

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.selfpro.realies.R
import com.selfpro.realies.SharedViewModel
import com.selfpro.realies.data.model.LoadState
import com.selfpro.realies.data.network.request.RealiesRequest
import com.selfpro.realies.feature.lazy.NewsThumbViewModel
import com.selfpro.realies.feature.main.realies.CategoryItem
import com.selfpro.realies.feature.main.realies.NewsThumbModelColumnItem
import com.selfpro.realies.feature.main.realies.RealiesSearchBox
import com.selfpro.realies.ui.color.SpColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChallengeScreen(
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
            RealiesSearchBox(modifier = Modifier.padding(horizontal = 15.dp), onClick = {
                sharedViewModel.mainNavController.navigate("search")
            })
        }
        stickyHeader {
            Row(
                modifier = Modifier
                    .background(color = SpColor.White)
                    .horizontalScroll(categoryScrollState)
                    .padding(vertical = 8.dp, horizontal = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CategoryItem("안녕")
                CategoryItem("Hello")
                CategoryItem("World!")
                CategoryItem("World!")
                CategoryItem("World!")
                CategoryItem("World!")
                CategoryItem("World!")
                CategoryItem("World!")
                CategoryItem("World!")
                CategoryItem("World!")
            }
        }
        when (newsState) {
            is LoadState.Loading -> {
                item {
                    Text(text = "Loading")
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
                }
            }

            is LoadState.Error -> {

            }
        }

    }
}