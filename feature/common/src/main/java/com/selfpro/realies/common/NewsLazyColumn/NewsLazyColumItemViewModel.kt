package com.selfpro.realies.common.NewsLazyColumn

import androidx.lifecycle.ViewModel
import com.selfpro.realies.util.common.SpLog
import com.selfpro.selfpro.data.domain.GenerateNewsTitleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class NewsLazyColumItemViewModel @Inject constructor(
    private val generateNewsTitleUseCase: GenerateNewsTitleUseCase
) : ContainerHost<NewsLazyColumnItemState, Nothing>, ViewModel() {
    override val container = container<NewsLazyColumnItemState, Nothing>(NewsLazyColumnItemState())

    fun generateNewsTitle(prompt: String) = intent {
        SpLog.d("Called generateNewsTitle")

        reduce { state.copy(isLoading = true) }

//        generateNewsTitleUseCase.invoke(prompt)
//            .onSuccess {
//                reduce {
//                    state.copy(
//                        isLoading = false,
//                        generatedNewsTitle = it
//                    )
//                }
//            }
    }

}