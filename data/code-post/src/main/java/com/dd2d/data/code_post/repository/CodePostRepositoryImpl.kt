package com.dd2d.data.code_post.repository

import com.dd2d.core.model.Pagination
import com.dd2d.core.state.DataState
import com.dd2d.core.state.asDataState
import com.dd2d.domain.code_post.model.CodePost
import com.dd2d.domain.code_post.model.CodePostCreateModel
import com.dd2d.domain.code_post.model.CodePostListItem
import com.dd2d.domain.code_post.model.CodePostListOption
import com.dd2d.domain.code_post.model.CodePostUpdateModel
import com.dd2d.domain.code_post.model_dummy.DummyCodePost
import com.dd2d.domain.code_post.model_dummy.DummyCodePostListItem
import com.dd2d.domain.code_post.repository.CodePostRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CodePostRepositoryImpl @Inject constructor(
    private val client: HttpClient
): CodePostRepository {
    override fun getCodePostList(
        options: CodePostListOption
    ): Flow<DataState<Pagination<CodePostListItem>>> = flow {
        delay(500)
        emit(
            Pagination(
                list = List(options.take) { DummyCodePostListItem.copy(id = options.page * options.take + it) },
                currentPage = options.page,
                totalPage = options.take,
                totalItemCount = Int.MAX_VALUE,
            )
        )
    }.asDataState()

    override fun getCodePost(id: Int): Flow<DataState<CodePost>> = flow {
        delay(500)
        emit(DummyCodePost.copy(id = id))
    }.asDataState()

    override fun createCodePost(model: CodePostCreateModel): Flow<DataState<Int>> = flow {
        delay(500)
        emit(1)
    }.asDataState()

    override fun updateCodePost(model: CodePostUpdateModel): Flow<DataState<Boolean>> = flow {
        delay(500)
        emit(true)
    }.asDataState()

    override fun deleteCodePost(id: Int): Flow<DataState<Boolean>> = flow {
        delay(500)
        emit(true)
    }.asDataState()
}