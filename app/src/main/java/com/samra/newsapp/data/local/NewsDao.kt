package com.samra.newsapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.samra.newsapp.data.network.model.Article
import com.samra.newsapp.data.network.model.ArticleList


@Dao
interface NewsDao {
    @Query("SELECT * FROM news_table")
    fun getAllLocalNews(): List<LocalNews>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertLocalNews(localNews: LocalNews)

    @Delete
    fun deleteLocalNews(localNews: LocalNews)

}