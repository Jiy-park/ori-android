package com.dd2d.core.presentation.refresh_lazy

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dd2d.core.model.Pagination
import com.dd2d.core.state.DataState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class RefreshLazyListManager <ListOptions, ListItemModel>(
    initialState: RefreshLazyListState = RefreshLazyListState.Idle,
    initialListOption: ListOptions,
    private val scope: CoroutineScope,
    private inline val flow: (options: ListOptions) -> Flow<DataState<Pagination<ListItemModel>>>,
) {
    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    var options = initialListOption
        private set

    val list = mutableStateListOf<ListItemModel>()

    var isLastPage by mutableStateOf(false)
        private set

    var totalItem by mutableIntStateOf(0)
        private set

    var totalPage by mutableIntStateOf(0)
        private set

    private fun getList(
        options: ListOptions,
        isRefresh: Boolean = false,
    ) {
        flow(options)
            .onEach { state ->
                when(state) {
                    is DataState.Loading -> {
                        _state.update {
                            if(isRefresh) RefreshLazyListState.Refreshing
                            else RefreshLazyListState.Loading
                        }
                    }
                    is DataState.Error -> _state.update { RefreshLazyListState.Error(state.exception) }
                    is DataState.Success -> {
                        if(isRefresh) { list.clear() }

                        with(state.data) {
                            this@RefreshLazyListManager.isLastPage = currentPage == totalPage
                            this@RefreshLazyListManager.totalPage = totalPage
                            this@RefreshLazyListManager.totalItem = totalItem
                            this@RefreshLazyListManager.list.addAll(list)
                        }

                        this@RefreshLazyListManager.options = options
                        _state.update { RefreshLazyListState.Idle }
                    }
                }
            }
            .launchIn(scope)
    }

    fun onRefresh(options: ListOptions) {
        getList(options = options, isRefresh = true)
    }

    fun loadMore(options: ListOptions) {
        if(isLastPage) return
        getList(options = options, isRefresh = false)
    }

    init {
        getList(options = options, isRefresh = false)
    }
}