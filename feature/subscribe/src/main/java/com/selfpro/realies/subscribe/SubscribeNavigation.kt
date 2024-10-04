package com.selfpro.realies.subscribe

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val SubscribeRoute = "subscribe_route"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.subscribeScreen(
    navHostController: NavHostController,
    enterTransition: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
) {
    composable(
        route = SubscribeRoute,
        enterTransition = enterTransition,
        exitTransition = { null }

    ) {
        SubscribeScreen(navHostController)
    }
}

