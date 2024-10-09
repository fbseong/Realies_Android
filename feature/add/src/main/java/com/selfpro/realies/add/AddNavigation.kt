package com.selfpro.realies.add

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.selfpro.realies.data.model.Route

const val AddRoute = "add_route"
private const val ScreenRoute = AddRoute

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addScreen(route: Route ) {
    val router = route.RouteData(ScreenRoute)

    composable(
        route = ScreenRoute,
        enterTransition = {null},
        exitTransition = { null }

    ) {
        AddScreen(router)
    }
}