package com.samra.newsapp.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.samra.newsapp.R
import com.samra.newsapp.constants.Constants
import com.samra.newsapp.databinding.FragmentLanguageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguageFragment : Fragment() {


    private lateinit var binding : FragmentLanguageBinding
    private lateinit var sharedPreferences : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLanguageBinding.inflate(layoutInflater)
        sharedPreferences = activity?.getSharedPreferences(Constants.sharedPreferences, Context.MODE_PRIVATE)!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.langEn.setOnClickListener{
            sharedPreferences.edit().putString(Constants.language, "en").apply()
            findNavController().popBackStack()
        }

        binding.langRu.setOnClickListener {
            sharedPreferences.edit().putString(Constants.language, "ru").apply()
            findNavController().popBackStack()
        }

        binding.btnBack.setOnClickListener{
            findNavController().popBackStack()
        }
    }


}