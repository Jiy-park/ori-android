package com.dd2d.domain.code_post.model

data class CodePostListItem(
    val id: Int,
    val author: CodePostAuthor,
    val title: String,
    val content: String,
    val createdAt: String,
    val updatedAt: String,
)
