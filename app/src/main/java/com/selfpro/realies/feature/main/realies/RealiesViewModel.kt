package com.selfpro.realies.feature.main.realies

import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.selfpro.realies.data.network.ClientRetrofit
import com.selfpro.realies.data.network.dto.RealiesDTO
import com.selfpro.realies.util.SpLog
import com.selfpro.realies.util.base.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RealiesViewModel : BaseViewModel() {
    private val _realiesFlow = MutableStateFlow<List<RealiesDTO>>(emptyList())
    val realeiesFlow: StateFlow<List<RealiesDTO>> = _realiesFlow

    fun getRecommendationRealies() = viewModelScope.async {
        kotlin.runCatching {
            ClientRetrofit.newsAPI.getNewsRecommendation(0)
        }.onSuccess {
            SpLog.d(it)
            _realiesFlow.value = it
        }.onFailure {
            SpLog.e(TAG, it)
        }
    }


//    private val generativeModel = GenerativeModel(
//        modelName = "gemini-pro-vision",
//        apiKey = BuildConfig.apiKey
//    )
}