package com.dd2d.domain.code_post.model

data class CodePostListOption(
    val page: Int = 1,
    val take: Int = 20,
    val sort: String = "",
    val keyword: String = ""
)
