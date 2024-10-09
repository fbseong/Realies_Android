package com.selfpro.realies.my

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.selfpro.realies.data.model.Route

const val MyRoute = "my_route"
private const val ScreenRoute = MyRoute

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.myScreen(
    route: Route,
) {
    val router = route.RouteData(ScreenRoute)

    composable(
        route = ScreenRoute,
        enterTransition = { route.slideTransition() },
        exitTransition = { null }

    ) {
        MyScreen(router)
    }
}