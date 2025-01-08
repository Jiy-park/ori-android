package com.dd2d.core.presentation.refresh_lazy

import com.dd2d.core.exception.ManagedException

sealed interface RefreshLazyListState {
    data object Idle: RefreshLazyListState
    data object Loading: RefreshLazyListState
    data object Refreshing: RefreshLazyListState
    data class Error(val exception: ManagedException): RefreshLazyListState
}