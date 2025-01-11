package com.dd2d.data.source.local.room.entity

import androidx.room.Entity

@Entity("sample_table")
data class SampleEntity(
    val id: Int,
    val data: String,
)
