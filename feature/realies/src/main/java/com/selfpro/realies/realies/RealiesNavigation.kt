package com.selfpro.realies.realies

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.selfpro.realies.data.model.Route

const val RealiesRoute = "realies_route"
private const val ScreenRoute = RealiesRoute

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.realiesScreen(
    route: Route,
) {
    val router = route.RouteData(ScreenRoute)

    composable(
        route = ScreenRoute,
        enterTransition = {route.slideTransition()},
        exitTransition = { null }

    ) {
        RealiesScreen(router)
    }
}

