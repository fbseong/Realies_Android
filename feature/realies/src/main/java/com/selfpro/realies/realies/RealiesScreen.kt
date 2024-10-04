package com.selfpro.realies.realies

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.selfpro.network.request.RealiesRequest
import com.selfpro.realies.common.NewsCategory
import com.selfpro.realies.common.NewsShimmerColumnItem
import com.selfpro.realies.common.RealiesSearchBox
import com.selfpro.realies.data.model.LoadState
import com.selfpro.realies.feature.assests.news.NewsColumnLazyItem
import com.selfpro.realies.search.SearchRoute
import com.selfpro.realies.shared.NetworkingViewModel
import com.selfpro.realies.util.common.SpColor
import com.selfpro.realies.util.common.SpLog
import com.selfpro.realies.util.icon.IcRealieslogo
import com.selfpro.realies.util.icon.SpIcon
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RealiesScreen(
    navHostController: NavHostController,
    viewModel: RealiesViewModel = hiltViewModel(),
    netWorkViewModel: NetworkingViewModel = hiltViewModel()
) {

    val categoryScrollState = rememberScrollState()

    val newsState by netWorkViewModel.collectAsState()



    LazyColumn(modifier = Modifier.background(color = SpColor.White)) {
        item {
            Image(
                imageVector = SpIcon.IcRealieslogo,
                contentDescription = "RealiesLogo",
                modifier = Modifier.padding(bottom = 10.dp, start = 15.dp)
            )
            RealiesSearchBox {
                navHostController.navigate(SearchRoute)
            }
        }
        stickyHeader {
            NewsCategory()
        }
        if (newsState.isLoading) {
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
        } else {
            val data = newsState.realiesList

            items(data.size) {
                NewsColumnLazyItem(modifier = Modifier, data = data[it], onClick = {
                    viewModel.url = it ?: ""
                    navHostController.navigate("news")
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
    }
}
