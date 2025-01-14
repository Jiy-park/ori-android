package com.dd2d.presentation.code_post.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.presentation.refresh_lazy.RefreshLazyListManager
import com.dd2d.domain.code_post.model.CodePostListOption
import com.dd2d.domain.code_post.repository.CodePostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class CodePostListViewModel @Inject constructor(
    private val codePostRepository: CodePostRepository
): ViewModel() {
    val codePostListManager = RefreshLazyListManager(
        initialListOption = CodePostListOption(),
        scope = viewModelScope,
        flow = codePostRepository::getCodePostList,
    )
    fun refresh() = with(codePostListManager) { onRefresh(options = options.copy(page = 1)) }
    fun loadMore() = with(codePostListManager) { loadMore(options = options.copy(page = options.page + 1)) }
    fun search(keyword: String) = with(codePostListManager) { onRefresh(options = options.copy(page = 1, keyword = keyword)) }
}
