package com.bezzo.moviecatalogue.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.moviecatalogue.data.model.TvShow


@Database(entities = [Movie::class, TvShow::class], version = 1)
abstract class LocalStorage: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: LocalStorage? = null

        private val sLock = Any()

        fun getInstance(context: Context): LocalStorage {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder<LocalStorage>(
                        context.applicationContext,
                        LocalStorage::class.java, "movies.db"
                    )
                        .build()
                }
                return INSTANCE!!
            }
        }
    }
}