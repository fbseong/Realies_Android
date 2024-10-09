package com.selfpro.realies.viewer

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.selfpro.realies.data.model.ViewerModel
import com.selfpro.realies.util.common.SpLog

@Composable
fun ViewerScreen (navHostController: NavHostController, viewerData: ViewerModel,){
    if (viewerData.newsType == ViewerModel.Type.Realies){

    }else{
        MajorViewerScreen(navHostController = navHostController, newsUrl = viewerData.newsUrl)
    }
}