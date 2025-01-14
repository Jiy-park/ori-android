package com.dd2d.app.navigation.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.dd2d.app.App
import com.dd2d.app.R
import com.dd2d.app.navigation.AppDestination
import com.dd2d.core.presentation.icon.VectorIcon
import com.dd2d.core.presentation.theme.MainColor
import com.dd2d.core.presentation.theme.MainGrey
import com.dd2d.presentation.code_post.list.CodePostListScreen
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

sealed interface MainDestination {
    @Serializable data object CodePost: MainDestination
}

private enum class MainBottomNavigationItem(
    @StringRes val labelRes: Int,
    @DrawableRes val iconRes: Int,
    val destination: MainDestination
) {
    CodePost(
        labelRes = com.dd2d.core.presentation.R.string.main_tab_code,
        iconRes = R.drawable.ic_code,
        destination = MainDestination.CodePost
    )
}

fun NavGraphBuilder.main(
    startDestination: MainDestination,
    modifier: Modifier = Modifier,
    navigateEvent: (destination: AppDestination) -> Unit,
) {
    composable<AppDestination.Main> {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                MainBottomAppBar(
                    startDestination = startDestination,
                    navController = navController,
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            modifier = modifier
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = startDestination,
                enterTransition = { fadeIn() },
                exitTransition = { fadeOut() },
                popEnterTransition = { fadeIn() },
                popExitTransition = { fadeOut() },
                modifier = Modifier
                    .consumeWindowInsets(innerPadding)
                    .fillMaxSize()
            ) {
                composable<MainDestination.CodePost> {
                    CodePostListScreen(modifier = Modifier.fillMaxSize()) {

                    }
                }
            }

        }

    }
}

@Composable
private fun MainBottomAppBar(
    startDestination: MainDestination,
    navController: NavController,
    modifier: Modifier = Modifier
) {
//    val currentDestination = navController.currentBackStackEntryAsState().value?.toRoute<MainDestination>()
    val currentDestination = startDestination // TODO("음....")
//    val currentDestination by navController.currentBackStackEntryFlow
//        .map { entry -> entry.toRoute<MainDestination>() }
//        .collectAsStateWithLifecycle(startDestination)

    BottomAppBar(
        containerColor = Color.LightGray.copy(alpha = 0.5F),
        contentPadding = PaddingValues(0.dp),
        modifier = modifier
    ) {
        NavigationBar(
            modifier = Modifier.fillMaxWidth()
        ) {
            MainBottomNavigationItem.entries.forEach { item ->
                NavigationBarItem(
                    selected = currentDestination == item.destination,
                    onClick = {
                        navController.navigate(item.destination) {
                            launchSingleTop = true
                            popUpTo(navController.graph.id) {
                                saveState = true
                            }
                        }
                    },
                    icon = {
                        VectorIcon(
                            res = item.iconRes,
                            contentDescription = stringResource(item.labelRes)
                        )
                    },
                    label = null,
                    alwaysShowLabel = false,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MainColor,
                        unselectedIconColor = MainGrey,
                        indicatorColor = MainColor.copy(alpha = 0.5F),
                    ),
                )
            }
        }
    }
}