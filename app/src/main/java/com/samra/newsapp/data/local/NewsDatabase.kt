package com.samra.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [LocalNews::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun NewsDao(): NewsDao
}