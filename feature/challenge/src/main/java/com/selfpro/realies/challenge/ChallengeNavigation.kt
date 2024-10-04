package com.selfpro.realies.challenge

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val ChallengeRoute = "challenge_route"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.challengeScreen(
    navHostController: NavHostController,
    enterTransition: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
) {
    composable(
        route = ChallengeRoute,
        enterTransition = enterTransition,
        exitTransition = { null }

    ) {
        ChallengeScreen(navHostController)
    }
}

