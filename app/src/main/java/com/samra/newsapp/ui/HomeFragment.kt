package com.samra.newsapp.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.samra.newsapp.adapter.NewsAdapter
import com.samra.newsapp.adapter.NewsCardAdapter
import com.samra.newsapp.adapter.OnCLickListener
import com.samra.newsapp.constants.Constants
import com.samra.newsapp.data.local.LocalNews
import com.samra.newsapp.data.network.model.Article
import com.samra.newsapp.data.network.model.ArticleList
import com.samra.newsapp.databinding.FragmentHomeBinding
import com.samra.newsapp.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var data : ArticleList
    private lateinit var topData : ArticleList
    private val cardsAdapter: NewsCardAdapter by lazy { NewsCardAdapter(topData.articles, object : OnCLickListener{
        override fun onClick(item: Article) {
            val localNews = LocalNews(0, item.author, item.content, item.description, item.publishedAt, item.title, item.url, item.urlToImage)
            val action = HomeFragmentDirections.actionHomeFragmentToNewsDetailFragment(localNews , false )
            findNavController().navigate(action)
        }

    }) }
    private val newsAdapter: NewsAdapter by lazy { NewsAdapter(data.articles, object : OnCLickListener{
        override fun onClick(item: Article) {
            val localNews = LocalNews(0, item.author, item.content, item.description, item.publishedAt, item.title, item.url, item.urlToImage)
            val action = HomeFragmentDirections.actionHomeFragmentToNewsDetailFragment(localNews , false)
            findNavController().navigate(action)
        }
    }) }
    private lateinit var recyclerViewCards: RecyclerView
    private lateinit var recyclerViewNews: RecyclerView
    private val  newsViewModel : NewsViewModel by viewModels()
    private lateinit var sharedPreferences : SharedPreferences
    private var language = "en"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        recyclerViewCards = binding.recyclerViewCards
        recyclerViewNews = binding.recyclerViewNews
        sharedPreferences = activity?.getSharedPreferences(Constants.sharedPreferences, Context.MODE_PRIVATE)!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data = ArticleList(listOf(), "", 0)
        topData = ArticleList(listOf(), "", 0)
        language = sharedPreferences.getString(Constants.language, "en").toString()
        Log.d("Semraaa", "salam")
        newsViewModel.getNews(Constants.query, language)
        binding.langText.setText(language.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        })
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                    newsViewModel.getNews(query, language)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Constants.query = newText
                return false
            }
        })
        binding.langText.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToLanguageFragment()
            findNavController().navigate(action)
        }
        newsViewModel.getTopNews(language)
        recyclerViewCards.adapter = cardsAdapter
        recyclerViewNews.adapter = newsAdapter


        newsViewModel.news.observe(viewLifecycleOwner, Observer {
            newsAdapter.onDataChange(it.articles)
        })

        newsViewModel.topNews.observe(viewLifecycleOwner, Observer{
            cardsAdapter.onDataChange(it.articles)
        })

    }
}
