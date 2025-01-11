package com.dd2d.data.source.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("sample_table")
data class SampleEntity(
    @PrimaryKey val id: Int,
    val data: String,
)
