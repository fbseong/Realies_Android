package com.selfpro.realies.common.NewsLazyColumn

import com.selfpro.network.request.RealiesRequest

data class NewsLazyColumnItemState(
    val isLoading: Boolean = false,
    val generatedNewsTitle: String = ""
)