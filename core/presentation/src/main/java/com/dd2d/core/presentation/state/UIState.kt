package com.dd2d.core.presentation.state

import com.dd2d.core.exception.ManagedException

sealed interface UIState {
    data object Idle: UIState
    data object Loading: UIState
    data class Error(val exception: ManagedException): UIState
    data object Success: UIState
}