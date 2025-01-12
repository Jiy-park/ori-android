package com.dd2d.domain.code_post.model_dummy

import com.dd2d.domain.code_post.model.CodePost
import com.dd2d.domain.code_post.model.CodePostAuthor

val DummyCodePost = CodePost(
    id = 1,
    author = DummyCodePostAuthor,
    title = "title",
    content = "content",
    createdAt = "2025-01-13 00:39:00",
    updatedAt = "2025-01-13 00:39:00",
)