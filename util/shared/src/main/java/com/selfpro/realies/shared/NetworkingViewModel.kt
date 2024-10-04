package com.selfpro.realies.shared

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.selfpro.realies.data.model.LoadState
import com.selfpro.realies.util.common.SpLog
import com.selfpro.selfpro.data.domain.GetRecommendationRealiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class NetworkingViewModel @Inject constructor(
    private val getRecommendationRealiesUseCase: GetRecommendationRealiesUseCase
) : ContainerHost<NetworkingState, NetworkingSideEffect>, ViewModel() {

    override val container = container<NetworkingState, NetworkingSideEffect>(NetworkingState())

    var url: String? = null


    private val _realiesFlow = MutableStateFlow<LoadState>(LoadState.Loading)
    val realeiesFlow = _realiesFlow.asStateFlow()

//    fun getRecommendationRealies(page: Int) = viewModelScope.launch {
//        getRecommendationRealiesUseCase.invoke(page)
//            .onSuccess {
//                _realiesFlow.value = LoadState.Success(it)
//            }
//            .onFailure {
//                SpLog.e("TAG", it)
//                _realiesFlow.value = LoadState.Error
//            }
//    }

    init {
        getRecommendationRealies(0)
    }

    fun getRecommendationRealies(page: Int) = intent {
        reduce { state.copy(isLoading = true) }
        getRecommendationRealiesUseCase.invoke(0)
            .onSuccess {
                reduce { state.copy(isLoading = false, realiesList = it) }
            }
            .onFailure {
                SpLog.e("TAG", it)
            }
    }


    private val _challengeFlow = MutableStateFlow<LoadState>(LoadState.Loading)
    val challengeFlow = _challengeFlow.asStateFlow()

    private val _subscribeFlow = MutableStateFlow<LoadState>(LoadState.Loading)
    val subscribeFlow = _subscribeFlow.asStateFlow()


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
        apiKey = "BuildConfig.apiKey"
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
