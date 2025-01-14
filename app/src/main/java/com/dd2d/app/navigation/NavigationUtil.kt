package com.dd2d.app.navigation

import androidx.navigation.NavController

fun NavController.safePopBackStack() {
    if (previousBackStackEntry != null) {
        popBackStack()
    }
}