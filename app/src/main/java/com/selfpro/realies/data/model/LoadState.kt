package com.selfpro.realies.data.model

sealed class LoadState {
    data object Loading : LoadState()
    data class Success(val data: Any) : LoadState()
    data object Error : LoadState()
}