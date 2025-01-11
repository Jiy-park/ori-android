package com.dd2d.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dd2d.data.source.local.room.entity.SampleEntity

@Dao
interface SampleDAO {
    @Query("SELECT * FROM sample_table")
    fun get(): List<SampleEntity>

    @Insert
    fun insert(entity: SampleEntity)
}