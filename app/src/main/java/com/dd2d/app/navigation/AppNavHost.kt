package com.dd2d.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dd2d.app.navigation.composable.MainDestination
import com.dd2d.app.navigation.composable.main

@Composable
fun AppNavHost(
    startDestination: AppDestination,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        main(
            startDestination = MainDestination.CodePost,
            navigateEvent = {

            },
            modifier = Modifier.fillMaxSize()
        )
    }
}