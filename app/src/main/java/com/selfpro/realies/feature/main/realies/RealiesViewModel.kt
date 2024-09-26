package com.selfpro.realies.feature.main.realies

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.selfpro.realies.BuildConfig
import com.selfpro.realies.data.model.LoadState
import com.selfpro.realies.data.network.repository.NewsAPIRepository
import com.selfpro.realies.util.SpLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RealiesViewModel @Inject constructor(
    private val newsAPIRepository: NewsAPIRepository
) : ViewModel() {

    var url: String? = null

    private val _realiesFlow = MutableStateFlow<LoadState>(LoadState.Loading)
    val realeiesFlow = _realiesFlow.asStateFlow()

    fun getRecommendationRealies(page: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                newsAPIRepository.getNewsRecommendation(page) // 주입된 클라이언트를 사용
            }.onSuccess {
                _realiesFlow.value = LoadState.Success(it)
            }.onFailure {
                SpLog.e("TAG", it)
                _realiesFlow.value = LoadState.Error
            }
        }
    }


    fun getProviderRealies(provider: String, loadStateFlow: MutableState<LoadState>) =
        viewModelScope.launch {
            kotlin.runCatching {
//                newsAPI.getNewsProvider(provider)
            }.onSuccess {
                loadStateFlow.value = LoadState.Success(it)
            }.onFailure {
                SpLog.e("TAG", it)
                loadStateFlow.value = LoadState.Error
            }
        }


    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.apiKey
    )

    fun getNewsTitle(
        loadStateFlow: MutableStateFlow<LoadState>,
        prompt: String
    ) {
        loadStateFlow.value = LoadState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = generativeModel.generateContent("다음 뉴스의 제목 1개 만들어줘 $prompt")

                response.text?.let { outputContent ->
                    SpLog.d("Success: $outputContent")
                    loadStateFlow.value = LoadState.Success(outputContent)
                }
            } catch (e: Exception) {
                loadStateFlow.value = LoadState.Error
            }
        }
    }
}
