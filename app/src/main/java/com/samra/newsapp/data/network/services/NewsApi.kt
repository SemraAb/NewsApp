package com.samra.newsapp.data.network.services

import com.samra.newsapp.constants.Constants
import com.samra.newsapp.data.network.model.ArticleList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("q") search: String,
        @Query("language") lang: String = "en",
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = Constants.apiKey
    ): Response<ArticleList>

    @GET("top-headlines")
    suspend fun getTopNews(
        @Query("country") country: String = "us",
        @Query("language") lang: String = "en",
        @Query("apiKey") apiKey: String = Constants.apiKey
    ): Response<ArticleList>
}