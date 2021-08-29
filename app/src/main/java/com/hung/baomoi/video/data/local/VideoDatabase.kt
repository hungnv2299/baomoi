package com.hung.baomoi.video.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hung.baomoi.video.data.model.Video

@Database(
    entities = [Video::class],
    version = 1
)
abstract class VideosDatabase : RoomDatabase() {
    abstract fun getVideoDao(): VideoDAO

    companion object {
        @Volatile
        private var instance: VideosDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            VideosDatabase::class.java,
            "videos_db.db"
        ).build()
    }
}