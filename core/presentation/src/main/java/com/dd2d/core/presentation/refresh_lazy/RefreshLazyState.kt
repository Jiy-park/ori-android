package com.dd2d.core.presentation.refresh_lazy

sealed interface RefreshLazyState {
    data object Loading: RefreshLazyState
    data object Refresh: RefreshLazyState
    data object Idle: RefreshLazyState
}