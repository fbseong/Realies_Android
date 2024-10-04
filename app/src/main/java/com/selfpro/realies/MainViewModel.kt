package com.selfpro.realies

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selfpro.realies.add.AddRoute
import com.selfpro.realies.challenge.ChallengeRoute
import com.selfpro.realies.data.model.NavData
import com.selfpro.realies.my.MyRoute
import com.selfpro.realies.realies.RealiesRoute
import com.selfpro.realies.subscribe.SubscribeRoute
import com.selfpro.realies.util.common.SpLog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // 길이가 2인 MutableList 생성
    val _navigationQueue = MutableStateFlow(listOf<String>())
    val navigationQueue = _navigationQueue.asStateFlow()

    // 큐에 값을 추가하는 함수
    fun addNavigationQueue(value: String) {
        val updatedQueue = _navigationQueue.value + value

        // 길이가 2를 초과하면 첫 번째 요소 제거
        _navigationQueue.value = if (updatedQueue.size > 2) updatedQueue.drop(1)
        else updatedQueue
    }

    fun getNavigationBounds() = _navigationQueue.value.run {
        Pair(getOrNull(0) ?: RealiesRoute, getOrNull(1)?.ifEmpty { first() } ?: RealiesRoute)
    }


    private val _bottomNavigationVisible = MutableStateFlow(true)
    val bottomNavigationVisible = _bottomNavigationVisible.asStateFlow()

    fun handleBottomNavigationVisibility(state: Boolean) {
        _bottomNavigationVisible.value = state
    }


    var navDataList = listOf(
        NavData(
            id = RealiesRoute,
            text = "릴리즈",
            icon = R.drawable.ic_navigation_realies,
            iconSelected = R.drawable.ic_navigation_realies_selected,
        ),
        NavData(
            id = SubscribeRoute,
            text = "구독",
            icon = R.drawable.ic_navigation_subscribe,
            iconSelected = R.drawable.ic_navigation_subscribe_selected,
        ),
        NavData(
            id = AddRoute,
            icon = R.drawable.ic_navigation_add,
        ),
        NavData(
            id = ChallengeRoute,
            text = "도전 뉴스",
            icon = R.drawable.ic_navigation_challenge,
            iconSelected = R.drawable.ic_navigation_challenge_selected,
        ),
        NavData(
            id = MyRoute,
            text = "나",
            icon = R.drawable.ic_navigation_my,
            iconSelected = R.drawable.ic_navigation_my_selected,
        ),
    )
}