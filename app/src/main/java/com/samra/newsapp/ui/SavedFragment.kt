package com.samra.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.samra.newsapp.adapter.NewsAdapterLocal
import com.samra.newsapp.adapter.OnCLickListener
import com.samra.newsapp.adapter.OnCLickListenerLocal
import com.samra.newsapp.data.local.LocalNews
import com.samra.newsapp.data.network.model.Article
import com.samra.newsapp.data.network.model.ArticleList
import com.samra.newsapp.databinding.FragmentSavedBinding
import com.samra.newsapp.viewModel.SavedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.Serializable

@AndroidEntryPoint
class SavedFragment : Fragment() {
    private lateinit var binding: FragmentSavedBinding
    private lateinit var newsAdapter: NewsAdapterLocal
    private lateinit var recyclerViewNews: RecyclerView
    private val  savedViewModel : SavedViewModel by viewModels()
    private lateinit var data : ArticleList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSavedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsAdapter = NewsAdapterLocal(listOf(), object : OnCLickListenerLocal{
            override fun onClick(item: LocalNews) {
                val action = SavedFragmentDirections.actionSavedFragmentToNewsDetailFragment(item , true)
                findNavController().navigate(action)
            }
        })
        recyclerViewNews = binding.recyclerViewNews
        recyclerViewNews.adapter = newsAdapter
        savedViewModel.savedNews()

        savedViewModel.savedNews.observe(viewLifecycleOwner, Observer {
            newsAdapter.onDataChange(it)
        })

    }
}