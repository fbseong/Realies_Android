package com.selfpro.realies

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.selfpro.realies.add.AddRoute
import com.selfpro.realies.add.addScreen
import com.selfpro.realies.challenge.challengeScreen
import com.selfpro.realies.data.model.Route
import com.selfpro.realies.my.myScreen
import com.selfpro.realies.realies.RealiesRoute
import com.selfpro.realies.realies.realiesScreen
import com.selfpro.realies.search.searchScreen
import com.selfpro.realies.subscribe.subscribeScreen
import com.selfpro.realies.viewer.viewerScreen

@Composable
fun MainNavHost(
    navHostController: NavHostController,
    modifier: Modifier,
    viewModel: MainViewModel = viewModel(),
) {
    val route = Route(navHostController,viewModel.navDataList)

    route.onNavigateListener { router ->
        viewModel.handleBottomNavigationVisibility(router != AddRoute)
    }

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = RealiesRoute,
    ) {
        realiesScreen(route = route)
        subscribeScreen(route = route)
        addScreen(route = route)
        challengeScreen(route = route,)
        myScreen(route = route,)
        searchScreen(navHostController)
        viewerScreen(navHostController)
    }
}

