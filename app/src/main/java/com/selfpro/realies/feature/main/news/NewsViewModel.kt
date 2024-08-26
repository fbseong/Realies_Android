package com.selfpro.realies.feature.main.news

import androidx.lifecycle.viewModelScope
import com.selfpro.realies.util.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class NewsViewModel: BaseViewModel() {
    private val _undoSharedFlow = MutableSharedFlow<Unit>()
    val undoSharedFlow = _undoSharedFlow.asSharedFlow()

    private val _redoSharedFlow = MutableSharedFlow<Unit>()
    val redoSharedFlow = _redoSharedFlow.asSharedFlow()

    fun undo() {
        viewModelScope.launch {
            _undoSharedFlow.emit(Unit)
        }
    }

    fun redo() {
        viewModelScope.launch {
            _redoSharedFlow.emit(Unit)
        }
    }
}