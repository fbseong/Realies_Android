package com.selfpro.realies.feature.main.realies

import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.selfpro.realies.data.network.ClientRetrofit
import com.selfpro.realies.data.network.dto.RealiesDTO
import com.selfpro.realies.util.SpLog
import com.selfpro.realies.util.UiState
import com.selfpro.realies.util.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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
            SpLog.d(it)
            _realiesFlow.value = it
        }.onFailure {
            SpLog.e(TAG, it)
        }
    }

    private val generativeModel = GenerativeModel(
        modelName = "gemini-pro-vision",
        apiKey = "AIzaSyCx3tI8bUSxNYnx6_nP-Vv0o9naNRglaBw"
    )

    fun getRealiesTitle(contents: String) {
        val prompt = "Summarize the following and make it a news title.\"${contents}\""

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = generativeModel.generateContent(
                    content {
                        text(prompt)
                    }
                )
                response.text?.let { outputContent ->
                    _uiState.value = UiState.Success(outputContent)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "")
            }
        }
    }


}