import com.samra.newsapp.data.network.model.ArticleList
import com.samra.newsapp.data.network.services.NewsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApi: NewsApi )  {

    fun getTopNews(country: String, lang: String): Flow<ArticleList> = flow {
            val result = newsApi.getTopNews( lang=lang ).body()
            if (result != null) {
                emit(result)
            }
    }

    fun getNews(search: String , lang: String):Flow<ArticleList> =flow {
            val result = newsApi.getNews(search=search ,lang=lang).body()
            if (result != null) {
                emit(result)
            }
    }


}
