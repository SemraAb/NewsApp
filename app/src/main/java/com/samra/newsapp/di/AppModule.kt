package com.samra.newsapp.di

import android.content.Context
import androidx.room.Room
import com.samra.newsapp.data.local.NewsDao
import com.samra.newsapp.data.local.NewsDatabase
import com.samra.newsapp.data.network.services.NewsApi
import com.samra.newsapp.data.network.services.NewsApiService
import com.samra.newsapp.repository.SavedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideNewsDao(db: NewsDatabase): NewsDao = db.NewsDao()

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): NewsDatabase =
        Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "SavedNewsDB"
        ).build()


    @Singleton
    @Provides
    fun provideRetrofit() =
        Retrofit.Builder().baseUrl(com.samra.newsapp.constants.Constants.baseUrl)
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit) = retrofit.create(NewsApi::class.java)

    @Singleton
    @Provides
    fun provideSavedRepository( newsDao: NewsDao): SavedRepository  = SavedRepository(newsDao)
//newsApiService: NewsApiService,


}