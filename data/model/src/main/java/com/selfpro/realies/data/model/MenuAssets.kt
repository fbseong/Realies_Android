package com.selfpro.realies.data.model

import android.net.Uri


sealed class MenuAssets {
    data class Image(val data: String = "", val imageList: List<Uri?> = listOf(null)) : MenuAssets()
    data class AIContentAssets(val data: String = "") : MenuAssets()
    data class MinTitle(val data: String = "") : MenuAssets()
    data class Text(val data: String = "") : MenuAssets()
}