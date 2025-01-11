package com.dd2d.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dd2d.data.source.local.room.dao.SampleDAO
import com.dd2d.data.source.local.room.entity.SampleEntity

@Database(
    entities = [
        SampleEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sampleDAO(): SampleDAO

    companion object {
        private var instance: AppDatabase? = null

        fun getDatabases(context: Context): AppDatabase {
            return instance?: synchronized(this) {
                Room.databaseBuilder(
                    context = context,
                    klass = AppDatabase::class.java,
                    name = "app_database"
                ).build()
                    .also { instance = it }
            }
        }
    }
}