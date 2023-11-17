package com.samra.newsapp.viewModel

import NewsRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samra.newsapp.data.local.NewsDao
import com.samra.newsapp.data.network.model.Article
import com.samra.newsapp.data.network.model.ArticleList
import com.samra.newsapp.data.network.services.NewsApi
import com.samra.newsapp.data.network.services.NewsApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsApi: NewsApi) : ViewModel() {

    var news = MutableLiveData<ArticleList>()
    var topNews = MutableLiveData<ArticleList>()
    fun getTopNews(language: String) {
        CoroutineScope(Dispatchers.IO).launch {
            NewsRepository(newsApi).getTopNews("us", language).collect() {
                topNews.postValue(it)
            }
        }
    }

    fun getNews(search: String, language: String) {
        CoroutineScope(Dispatchers.IO).launch {
            NewsRepository(newsApi).getNews(search, language).collect() {
                news.postValue(it)
            }
        }
    }
}