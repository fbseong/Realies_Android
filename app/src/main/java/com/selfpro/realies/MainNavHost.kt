package com.selfpro.realies

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.selfpro.realies.add.addScreen
import com.selfpro.realies.challenge.challengeScreen
import com.selfpro.realies.data.model.NavData
import com.selfpro.realies.my.myScreen
import com.selfpro.realies.realies.RealiesRoute
import com.selfpro.realies.realies.realiesScreen
import com.selfpro.realies.search.searchScreen
import com.selfpro.realies.subscribe.subscribeScreen
import com.selfpro.realies.util.common.SpLog

@Composable
fun MainNavHost(
    navHostController: NavHostController,
    modifier: Modifier,
    viewModel: MainViewModel = viewModel()
) {
    viewModel.run {
        NavHost(
            modifier = modifier,
            navController = navHostController,
            startDestination = RealiesRoute,
        ) {
            realiesScreen(navHostController) { enterAnimate(getNavigationBounds(), navDataList) }
            subscribeScreen(navHostController) { enterAnimate(getNavigationBounds(), navDataList) }
            addScreen(navHostController)
            challengeScreen(navHostController) { enterAnimate(getNavigationBounds(), navDataList) }
            myScreen(navHostController) { enterAnimate(getNavigationBounds(), navDataList) }
            searchScreen(navHostController)
        }
    }
}

fun enterAnimate(screenId: Pair<String, String>, navDataList: List<NavData>) =
    fadeIn() + screenId
        .run { navDataList.indexOfFirst { it.id == first } to navDataList.indexOfFirst { it.id == second } }
        .run { slideInHorizontally { (if (first < second) it else -it) / 30 } }