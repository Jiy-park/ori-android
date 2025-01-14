package com.dd2d.core.presentation.refresh_lazy

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.R
import com.dd2d.core.presentation.theme.MainColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefreshLazyColumn(
    onLoad: () -> Unit,
    onRefresh: () -> Unit,
    refreshLazyListState: RefreshLazyListState,
    modifier: Modifier = Modifier,
    lazyState: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement. Vertical = if (!reverseLayout) Arrangement. Top else Arrangement. Bottom,
    horizontalAlignment: Alignment. Horizontal = Alignment. Start,
    flingBehavior: FlingBehavior = ScrollableDefaults. flingBehavior(),
    userScrollEnabled: Boolean = true,
    refreshState: PullToRefreshState = rememberPullToRefreshState(),
    refreshIndicator: @Composable BoxScope.() -> Unit = {
        Indicator(
            modifier = Modifier.align(Alignment.TopCenter),
            isRefreshing = refreshLazyListState is RefreshLazyListState.Refreshing,
            state = refreshState,
            color = MainColor,
            containerColor = Color.White,
        )
    },
    content: LazyListScope.() -> Unit,
) {
    val scope = rememberCoroutineScope()
    var innerLoading by remember { mutableStateOf(false) }

    val visibleMoveTopButton by remember {
        derivedStateOf {
            !lazyState.isScrollInProgress && lazyState.canScrollBackward
        }
    }

    LaunchedEffect(key1 = refreshLazyListState) {
        innerLoading = refreshLazyListState is RefreshLazyListState.Loading
    }

    LaunchedEffect(key1 = lazyState) {
        snapshotFlow { lazyState.layoutInfo.visibleItemsInfo.last().index }
            .filter { lastVisibleItemIndex ->
                lastVisibleItemIndex >= lazyState.layoutInfo.totalItemsCount * 0.5
            }
            .collect {
                if(!innerLoading) {
                    innerLoading = true
                    onLoad()
                }
            }
    }

    PullToRefreshBox(
        isRefreshing = refreshLazyListState is RefreshLazyListState.Refreshing,
        state = refreshState,
        onRefresh = onRefresh,
        indicator = refreshIndicator,
        modifier = modifier
    ) {
        LazyColumn(
            content = content,
            state = lazyState,
            contentPadding = contentPadding,
            reverseLayout = reverseLayout,
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            flingBehavior = flingBehavior,
            userScrollEnabled = userScrollEnabled,
            modifier = Modifier.matchParentSize()
        )
        AnimatedVisibility(
            visible = visibleMoveTopButton,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-30).dp)
                .size(35.dp)
                .clip(CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = stringResource(R.string.move_to_top),
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable { scope.launch { lazyState.animateScrollToItem(0) } }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun RefreshLazyPrev() {
    var page by remember { mutableStateOf(1) }
    val take = 30
    val list = remember {
        List(take) { it }.toMutableStateList()
    }
    LaunchedEffect(list.size) {
        Log.i("LOG_CHECK", "RefreshLazyPrev: list :$list")
    }
    
    LaunchedEffect(page) {
        Log.i("LOG_CHECK", "RefreshLazyPrev: page:$page")
    }
    val scope = rememberCoroutineScope()
    var state by remember { mutableStateOf<RefreshLazyListState>(RefreshLazyListState.Idle) }
    
    Log.v("LOG_CHECK", "state : $state: ")
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        RefreshLazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.33F),
            onLoad = {
                Log.i("LOG_CHECK", "RefreshLazyPrev: on load")
                scope.launch {
                    state = RefreshLazyListState.Loading
                    delay(100)
                    list.addAll(List(take) { page * take + it })
                    Log.wtf("LOG_CHECK", "RefreshLazyPrev: list :$list")
                    page ++
                    state = RefreshLazyListState.Idle
                }
            },
            onRefresh = {
                scope.launch {
                    state = RefreshLazyListState.Refreshing
                    delay(500)
                    list.clear()
                    list.addAll(List(take) { it })
                    page = 1
                    state = RefreshLazyListState.Idle
                }
            },
            refreshLazyListState = state
        ) {
            items(items = list, key = { it }) {
                Text(text = it.toString(), modifier= Modifier
                    .fillMaxWidth()
                    .height(50.dp))
            }
        }

    }
}