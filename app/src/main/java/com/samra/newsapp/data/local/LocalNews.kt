package com.samra.newsapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "news_table")
data class LocalNews(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "content") val content: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "published_at") val publishedAt: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "urlToImage") val urlToImage: String?,
) : Serializable
