package com.dd2d.core.presentation.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

interface UIStateManager {
    val state: MutableStateFlow<UIState>

    fun toIdle() = state.update { UIState.Idle }
    fun toLoading() = state.update { UIState.Loading }
    fun toSuccess() = state.update { UIState.Success }
}