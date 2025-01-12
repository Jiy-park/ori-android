package com.dd2d.app.navigation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dd2d.app.navigation.AppDestination
import com.dd2d.core.presentation.main_text.Main700Text

fun NavGraphBuilder.main(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    composable<AppDestination.Main> {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ){
            Main700Text(text = "메인 화면 in ori-android")
        }
    }
}