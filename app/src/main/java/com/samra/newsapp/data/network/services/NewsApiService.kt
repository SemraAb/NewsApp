package com.samra.newsapp.data.network.services

import com.samra.newsapp.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsApiService {
    val retrofitInstance  by lazy {
        Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(NewsApi::class.java)
    }
}