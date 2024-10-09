package com.selfpro.realies.data.model

import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class Route(val navHostController: NavHostController,val navDataList: List<NavData>) {
    val _launchedList = MutableStateFlow(navDataList.map { LaunchedModel(it.id, false) })

    fun isLaunched(id: String): Boolean {
        val isLaunched = _launchedList.value.find { it.id == id }?.isLaunched ?: true
        _launchedList.value =
            _launchedList.value.map { if (it.id == id) it.copy(isLaunched = true) else it }
        return isLaunched
    }

    open inner class RouteData(val route: String, val controller: NavHostController = navHostController) {
        fun launched(function: () -> Unit) {
            if (!isLaunched(route)) {
                function()
            }
        }
    }
    fun onNavigateListener(settings: (String?) -> Unit) {
        navHostController.addOnDestinationChangedListener { controller, destination, arguments ->
            settings(destination.route)
            addNavigationQueue(destination.route)
        }
    }

    // 길이가 2인 MutableList 생성
    val _navigationQueue = MutableStateFlow(listOf<String>())
    val navigationQueue = _navigationQueue.asStateFlow()

    // 큐에 값을 추가하는 함수
    fun addNavigationQueue(value: String?) {
        // 바로 앞의 값과 비교
        if (_navigationQueue.value.lastOrNull() != (value ?: "")) {
            val updatedQueue = _navigationQueue.value + (value?:"")
            _navigationQueue.value = if (updatedQueue.size > 2) updatedQueue.drop(1)
            else updatedQueue
        }
    }

    private fun getNavigationBounds() = _navigationQueue.value.run {
        Pair(getOrNull(0) ?: navDataList[0].id, getOrNull(1)?.ifEmpty { first() } ?: navDataList[0].id)
    }

    fun slideTransition() = enterAnimate(getNavigationBounds(), navDataList)

    fun enterAnimate(screenId: Pair<String, String>, navDataList: List<NavData>) =
        fadeIn() + screenId
            .run { navDataList.indexOfFirst { it.id == first } to navDataList.indexOfFirst { it.id == second } }
            .run { slideInHorizontally { (if (first < second) it else -it) / 30 } }

}