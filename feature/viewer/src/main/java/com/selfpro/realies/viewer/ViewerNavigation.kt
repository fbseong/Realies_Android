package com.selfpro.realies.viewer

import android.net.Uri
import android.view.View
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.selfpro.realies.data.model.ViewerModel

const val ViewerRoute = "viewer_route/{newsType}/{newsUrl}"

fun NavHostController.navigateToViewer(
    newsType: String,
    newsUrl: String,
) {
    this.navigate("viewer_route/$newsType/${Uri.encode(newsUrl)}")
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.viewerScreen(
    navHostController: NavHostController,
) {
    composable(
        route = ViewerRoute,
        arguments = listOf(
            navArgument("newsType") { type = NavType.StringType },
            navArgument("newsUrl") { type = NavType.StringType }
        )
    ) {
        ViewerScreen(
            navHostController = navHostController,
            viewerData = ViewerModel(
                it.arguments?.getString("newsType") ?: ViewerModel.Type.Realies,
                it.arguments?.getString("newsUrl") ?: ""
            )
        )
    }
}