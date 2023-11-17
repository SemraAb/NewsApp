package com.samra.newsapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.bumptech.glide.Glide
import com.samra.newsapp.R
import com.samra.newsapp.data.local.LocalNews
import com.samra.newsapp.data.local.NewsDatabase
import com.samra.newsapp.data.network.model.Article
import com.samra.newsapp.databinding.FragmentNewsDetailBinding
import com.samra.newsapp.viewModel.SavedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : Fragment() {
    private lateinit var binding: FragmentNewsDetailBinding
    private lateinit var data: LocalNews
    private val savedViewModel: SavedViewModel by viewModels()
    private var isFromSaved: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        data = arguments?.getSerializable("item") as LocalNews
        isFromSaved = arguments?.getSerializable("isFromSaved") as Boolean

        Glide.with(requireActivity().applicationContext)
            .load(data.urlToImage)
            .into(binding.newsImage)
//        Picasso.get().load(data.urlToImage).into(binding.newsImage)
        binding.textTitle.setText(data.title)
        binding.textTitle2.setText(data.title)
        binding.textAuthor.setText(data.author)
        binding.textExpert.setText(data.description)
        binding.textSummary.setText(data.content)
        binding.readMoreButton.setOnClickListener {
            val url = data.url
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        popupMenu()
    }

    private fun popupMenu() {
        binding.moreMenu.setOnClickListener {
            var popupMenu = PopupMenu(requireContext(), binding.moreMenu)
            popupMenu.menuInflater.inflate(R.menu.more, popupMenu.menu)
            try {
                val fieldMPH = popupMenu.javaClass.getDeclaredField("mPopup")
                fieldMPH.isAccessible = true
                val mph = fieldMPH.get(popupMenu)
                mph.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(mph, true)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            if(isFromSaved){
                popupMenu.menu.get(0).setIcon(R.drawable.ic_save_filled)
                popupMenu.menu.get(0).title = "Delete"
            }else{
                popupMenu.menu.get(0).setIcon(R.drawable.ic_save_empty)
                popupMenu.menu.get(0).title = "Save"
            }

            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.save -> {
                        if (isFromSaved) {
                            savedViewModel.deleteNews(data)
                            findNavController().popBackStack()
                        } else {
                            savedViewModel.insertNews(data)
                        }
                        true
                    }

                    R.id.share -> {
                        true
                    }

                    else -> false
                }
            }

            popupMenu.show();
        }
    }
}