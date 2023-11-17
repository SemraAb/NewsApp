package com.samra.newsapp.repository

import com.samra.newsapp.data.local.LocalNews
import com.samra.newsapp.data.local.NewsDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


class SavedRepository @Inject constructor(private val newsDao: NewsDao) {
     fun getAll( ) : Flow<List<LocalNews>> = flow {
             var result = newsDao.getAllLocalNews()
             if (result != null) {
                 emit(result)
             }
    }
     fun insert(localNews: LocalNews){
             newsDao.insertLocalNews(localNews)
    }
     fun delete(localNews: LocalNews) {
             newsDao.deleteLocalNews(localNews)
     }
}