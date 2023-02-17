package com.example.demoapp.data.repository

import com.example.demoapp.data.model.ArticlesDetailsRes
import com.example.demoapp.data.model.TopArticlesIdRes
import com.example.demoapp.data.remote.ApiInterface
import com.example.demoapp.domain.repository.ArticlesRepository

/*
   Implemented class call retrofit client for api call
 */

class ArticlesRepositoryImpl(private val apiInterface: ApiInterface) : ArticlesRepository {

    /*
      This function call articlesIdList api and returns TopArticlesIdRes as result
      */
    override suspend fun getTopArticlesIdList(): TopArticlesIdRes {
        return apiInterface.getTopArticlesIdList()
    }

    /*
     This function call articlesDetails api and returns ArticlesDetailsRes as result
     */
    override suspend fun getTopArticlesDetails(id: Int): ArticlesDetailsRes {
        return apiInterface.getTopArticlesDetails(id)
    }
}