package com.selfpro.realies.realies

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.selfpro.network.request.RealiesRequest
import com.selfpro.realies.common.NewsCategory
import com.selfpro.realies.common.NewsShimmerColumnItem
import com.selfpro.realies.common.RealiesSearchBox
import com.selfpro.realies.data.model.Route
import com.selfpro.realies.data.model.ViewerModel
import com.selfpro.realies.feature.assests.news.NewsLazyColumnItem
import com.selfpro.realies.search.SearchRoute
import com.selfpro.realies.util.common.SpColor
import com.selfpro.realies.util.common.SpLog
import com.selfpro.realies.util.icon.IcRealieslogo
import com.selfpro.realies.util.icon.SpIcon
import com.selfpro.realies.viewer.navigateToViewer
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RealiesScreen(
    router: Route.RouteData,
    viewModel: RealiesViewModel = hiltViewModel(),
) {
    val newsState by viewModel.collectAsState()

    var savedNewsState by rememberSaveable { mutableStateOf<List<RealiesRequest>>(emptyList()) }
    savedNewsState = newsState.realiesList

    SpLog.d(newsState)

    router.launched { viewModel.getRecommendationRealies(0) }

    LazyColumn(modifier = Modifier.background(color = SpColor.White)) {
        item {
            Image(
                imageVector = SpIcon.IcRealieslogo,
                contentDescription = "RealiesLogo",
                modifier = Modifier.padding(bottom = 10.dp, start = 15.dp)
            )
            RealiesSearchBox {
                router.controller.navigate(SearchRoute)
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
            items(
                items = savedNewsState,
            ) {
                NewsLazyColumnItem(
                    modifier = Modifier,
                    data = it,
                    onClick = { url ->
                        router.controller.navigateToViewer(ViewerModel.Type.Major, url ?: "")
                    },
                )
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
