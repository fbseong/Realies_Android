package com.selfpro.realies.challenge

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.selfpro.realies.data.model.Route

const val ChallengeRoute = "challenge_route"
private const val ScreenRoute = ChallengeRoute

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.challengeScreen(
    route: Route,
) {
    val router = route.RouteData(ScreenRoute)

    composable(
        route = ScreenRoute,
        enterTransition = {route.slideTransition()},
        exitTransition = { null }

    ) {
        ChallengeScreen(router)
    }
}