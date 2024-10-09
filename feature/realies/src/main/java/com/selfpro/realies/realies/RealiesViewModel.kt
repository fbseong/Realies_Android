package com.selfpro.realies.realies

import androidx.lifecycle.ViewModel
import com.selfpro.realies.util.common.SpLog
import com.selfpro.selfpro.data.domain.GetRecommendationRealiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class RealiesViewModel @Inject constructor(
    private val getRecommendationRealiesUseCase: GetRecommendationRealiesUseCase,
//    private val realiesPreferences: RealiesPreferencesDataSource
) : ContainerHost<RealiesState, Nothing>, ViewModel() {

    override val container = container<RealiesState, Nothing>(RealiesState())


    fun getRecommendationRealies(page: Int) = intent {

        reduce { state.copy(isLoading = true) }
        getRecommendationRealiesUseCase.invoke(0)
            .onSuccess {
                SpLog.d("Advadted: $it")
                reduce { state.copy(isLoading = false, realiesList = it) }
            }
            .onFailure {
                SpLog.e("TAG", it)
            }
    }

//    init {
//        hello()
//        heli()
//    }

//    fun hello() = intent {
//        realiesPreferences.saveTitle("hello")
//    }
//    fun heli() = intent {
//        SpLog.d(realiesPreferences.title)
//    }


}