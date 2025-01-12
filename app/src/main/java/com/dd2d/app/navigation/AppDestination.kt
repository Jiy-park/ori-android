package com.dd2d.app.navigation

import kotlinx.serialization.Serializable

sealed interface AppDestination {
    @Serializable data object Main: AppDestination
}