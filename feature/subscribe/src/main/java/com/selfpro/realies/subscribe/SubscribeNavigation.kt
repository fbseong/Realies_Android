package com.selfpro.realies.subscribe

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.selfpro.realies.data.model.Route

const val SubscribeRoute = "subscribe_route"
private const val ScreenRoute = SubscribeRoute

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.subscribeScreen(
    enterTransition: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    route: Route
) {
    val router = route.RouteData(ScreenRoute)

    composable(
        route = router.route,
        enterTransition = {route.slideTransition()},
        exitTransition = { null }
    ) {
        SubscribeScreen(router)
    }
}

