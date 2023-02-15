package com.example.demoapp.hilt

import androidx.viewbinding.BuildConfig
import com.example.demoapp.data.remote.ApiInterface
import com.example.demoapp.data.repository.ArticlesRepositoryImpl
import com.example.demoapp.domain.repository.ArticlesRepository
import com.example.demoapp.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltModule {

    @Provides
    @Singleton
    fun provideNewsAPI():ApiInterface{

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideArticlesListRepository(apiInterface: ApiInterface): ArticlesRepository {
        return ArticlesRepositoryImpl(apiInterface)
    }
}