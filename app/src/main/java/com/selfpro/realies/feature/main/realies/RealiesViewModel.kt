package com.selfpro.realies.feature.main.realies

import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.selfpro.realies.data.network.ktor.ClientKtor
import com.selfpro.realies.data.network.retrofit.ClientRetrofit
import com.selfpro.realies.data.network.request.RealiesDTO
import com.selfpro.realies.util.SpLog
import com.selfpro.realies.util.UiState
import com.selfpro.realies.util.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RealiesViewModel : BaseViewModel() {
    private val _realiesFlow = MutableStateFlow<List<RealiesDTO>>(emptyList())
    val realeiesFlow: StateFlow<List<RealiesDTO>> = _realiesFlow

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun getRecommendationRealies() = viewModelScope.async {
        kotlin.runCatching {
            ClientRetrofit.newsAPI.getNewsRecommendation(0)
        }.onSuccess {
            _realiesFlow.value = it
        }.onFailure {
            SpLog.e(TAG, it)
        }
    }

    fun getSummarizedTitle(
        _titleSummaryFLow: MutableSharedFlow<String>,
        title: String,
        content: String
    ) =
        viewModelScope.async {
            kotlin.runCatching {
                ClientKtor.summarize(title, content)
            }.onSuccess {
                _titleSummaryFLow.emit(it)
            }.onFailure {
                SpLog.e(TAG, it)
            }
        }

    fun removeDots(inputString: String): String {
        var newString = inputString.replace(".", "")
        newString = newString.replace(",", "")

        return newString
    }


}