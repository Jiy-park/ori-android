package com.dd2d.domain.code_post.repository

import com.dd2d.core.model.Pagination
import com.dd2d.core.state.DataState
import com.dd2d.domain.code_post.model.CodePost
import com.dd2d.domain.code_post.model.CodePostCreateModel
import com.dd2d.domain.code_post.model.CodePostListItem
import com.dd2d.domain.code_post.model.CodePostListOption
import com.dd2d.domain.code_post.model.CodePostUpdateModel
import kotlinx.coroutines.flow.Flow

interface CodePostRepository {
    fun getCodePostList(options: CodePostListOption): Flow<DataState<Pagination<CodePostListItem>>>
    fun getCodePost(id: Int): Flow<DataState<CodePost>>
    fun createCodePost(model: CodePostCreateModel): Flow<DataState<Int>>
    fun updateCodePost(model: CodePostUpdateModel): Flow<DataState<Boolean>>
    fun deleteCodePost(id: Int): Flow<DataState<Boolean>>
}