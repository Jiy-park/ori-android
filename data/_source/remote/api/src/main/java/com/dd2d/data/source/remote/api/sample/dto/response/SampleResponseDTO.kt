package com.dd2d.data.source.remote.api.sample.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class SampleResponseDTO(
    val id: Int,
    val data: String,
)
