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

const val MyRoute = "my_route"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.myScreen(
    navHostController: NavHostController,
    enterTransition: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
) {
    composable(
        route = MyRoute,
        enterTransition =  enterTransition ,
        exitTransition = { null }

    ) {
        MyScreen(navHostController)
    }
}
