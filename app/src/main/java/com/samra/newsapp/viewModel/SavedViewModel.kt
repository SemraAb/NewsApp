package com.samra.newsapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samra.newsapp.data.local.LocalNews
import com.samra.newsapp.data.network.model.ArticleList
import com.samra.newsapp.repository.SavedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(private val savedRepo: SavedRepository) : ViewModel() {

    var savedNews = MutableLiveData<List<LocalNews>>()

    fun savedNews() {
        CoroutineScope(Dispatchers.IO).launch {
            savedRepo.getAll().collect() {
                savedNews.postValue(it)
            }
        }
    }
    fun insertNews(localNews: LocalNews) {
        CoroutineScope(Dispatchers.IO).launch {
            savedRepo.insert(localNews)
        }

    }
    fun deleteNews(localNews: LocalNews) {
        CoroutineScope(Dispatchers.IO).launch {
            savedRepo.delete(localNews)
        }
    }
}

