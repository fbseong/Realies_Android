package com.selfpro.realies.feature.main.realies

import androidx.lifecycle.viewModelScope
import com.selfpro.realies.data.network.retrofit.ClientRetrofit
import com.selfpro.realies.data.network.request.RealiesRequest
import com.selfpro.realies.util.SpLog
import com.selfpro.realies.util.base.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RealiesViewModel : BaseViewModel() {
    private val _realiesFlow = MutableStateFlow<List<RealiesRequest>>(emptyList())
    val realeiesFlow: StateFlow<List<RealiesRequest>> = _realiesFlow


    fun getRecommendationRealies() = viewModelScope.async {
        kotlin.runCatching {
            ClientRetrofit.newsAPI.getNewsRecommendation(0)
        }.onSuccess {
            _realiesFlow.value = it
        }.onFailure {
            SpLog.e(TAG, it)
        }
    }
}