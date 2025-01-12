package com.dd2d.domain.code_post.model

data class CodePost(
    val id: Int,
    val author: CodePostAuthor,
    val title: String,
    val content: String,
    val createdAt: String,
    val updatedAt: String,
) {
    fun getUpdateModel(
        title: String = this.title,
        content: String = this.content,
    ): CodePostUpdateModel = CodePostUpdateModel(
        id = this.id,
        title = title,
        content = content,
    )
}
