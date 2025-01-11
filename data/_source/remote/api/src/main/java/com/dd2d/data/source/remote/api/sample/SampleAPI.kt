package com.dd2d.data.source.remote.api.sample

import com.dd2d.data.source.remote.api.sample.dto.response.SampleResponseDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class SampleAPI @Inject constructor(
    private val client: HttpClient,
) {
    suspend fun getSample(): SampleResponseDTO {
        val response = client.get("some url"){
            parameter(key = "some key", value = "some value")
        }
        return response.body<SampleResponseDTO>()
    }
}