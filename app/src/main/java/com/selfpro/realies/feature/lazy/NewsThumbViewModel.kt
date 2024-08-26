package com.selfpro.realies.feature.lazy

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.selfpro.realies.data.model.LoadState
import com.selfpro.realies.data.network.request.NaverSummarizeRequest
import com.selfpro.realies.data.network.request.NaverSummarizeRequest.Option
import com.selfpro.realies.data.network.request.NaverSummarizeRequest.Document
import com.selfpro.realies.data.network.response.NaverSummarizeResponse
import com.selfpro.realies.data.network.retrofit.ClientRetrofit
import com.selfpro.realies.util.SpLog
import com.selfpro.realies.util.base.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class NewsThumbViewModel : BaseViewModel() {
    private val _realiesFlow = MutableStateFlow<LoadState>(LoadState.Loading)
    val realeiesFlow = _realiesFlow.asStateFlow()

    fun getRecommendationRealies(page: Int) = viewModelScope.launch {
        kotlin.runCatching {
            ClientRetrofit.newsAPI.getNewsRecommendation(page)
        }.onSuccess {
            _realiesFlow.value = LoadState.Success(it)
        }.onFailure {
            SpLog.e(TAG, it)
            _realiesFlow.value = LoadState.Error
        }
    }

    fun getSummarizedTitle(loadStateFlow: MutableStateFlow<LoadState>, title: String, content: String) = viewModelScope.launch {
        kotlin.runCatching {
            ClientRetrofit.naverOpenAI.getSummarizedTitle(
                naverSummarizeData =
                    NaverSummarizeRequest(Document(title, content), Option(),
                )
            )
        }.onSuccess {
            loadStateFlow.value = LoadState.Success(it)
        }.onFailure {
            loadStateFlow.value = LoadState.Error
            SpLog.e(TAG, it)
        }
    }
}