package com.dd2d.presentation.code_post.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.core.presentation.theme.MainGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodePostListScreen(
    modifier: Modifier = Modifier,
    onItemClick: (id: Int) -> Unit,
) {
    val viewModel: CodePostListViewModel = hiltViewModel()
    val state by viewModel.codePostListManager.state.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = MainGrey,
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(com.dd2d.core.presentation.R.drawable.app_icon),
                        contentDescription = null,
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                )
            )
        },
        modifier = modifier
    ) { innerPadding ->
        CodePostListScreenContent(
            state = state,
            list = viewModel.codePostListManager.list,
            onRefresh = viewModel::refresh,
            onLoadMore = viewModel::loadMore,
            onSearch = viewModel::search,
            onItemClick = onItemClick,
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
        )
    }
}