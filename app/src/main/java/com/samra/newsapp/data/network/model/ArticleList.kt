package com.samra.newsapp.data.network.model

data class ArticleList(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)