package com.dd2d.domain.code_post.model_dummy

import com.dd2d.domain.code_post.model.CodePostListItem

val DummyCodePostListItem = with(DummyCodePost) {
    CodePostListItem(
        id = this.id,
        author = this.author,
        title = this.title,
        content = this.content,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
    )
}