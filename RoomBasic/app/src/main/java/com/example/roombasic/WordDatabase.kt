package com.example.roombasic

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//单列模式
@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        private var INSTANCE: WordDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): WordDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    WordDatabase::class.java, "word_database"
                )
                    .build()
            }
            return INSTANCE
        }
    }
}