package com.selfpro.realies.realies

import com.selfpro.network.request.RealiesRequest

data class RealiesState(
    val isLoading: Boolean = false,
    val realiesList: List<RealiesRequest> = emptyList()
)
