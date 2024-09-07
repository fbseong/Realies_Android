package com.selfpro.realies.data.model

sealed class NewsAddMenuAssetsModel {
    data object ImageAssets: NewsAddMenuAssetsModel()
    data object AIContentAssets: NewsAddMenuAssetsModel()
    data object StartTextAssets: NewsAddMenuAssetsModel()
    data object TextAssets: NewsAddMenuAssetsModel()
}