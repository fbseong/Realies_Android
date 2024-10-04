package com.selfpro.realies.add

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val AddRoute = "add_route"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addScreen(
    navHostController: NavHostController,
    enterTransition: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
) {
    composable(
        route = AddRoute,
        enterTransition = enterTransition,
        exitTransition = { null }

    ) {
        AddScreen(navHostController)
    }
}