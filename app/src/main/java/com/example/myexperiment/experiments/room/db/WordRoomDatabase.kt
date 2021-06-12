package com.example.myexperiment.experiments.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 * Created by nampham on 5/15/21.
 * khi thực hiện modify database schema. chúng ta cần update version number và định nghĩa migration strategy
 */

@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
public abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {

        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(ctx: Context): WordRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    WordRoomDatabase::class.java, "word_database"
                ).build()
                INSTANCE  = instance
                instance
            }
        }
    }
}