package com.example.demoapp.domain.repository

import com.example.demoapp.data.model.ArticlesDetailsRes
import com.example.demoapp.data.model.TopArticlesIdRes

/*
   Interface contains method that are implemented in Repository Implementation class
 */
interface ArticlesRepository {

    /*
      This function returns TopArticlesIdRes as result
      */
    suspend fun getTopArticlesIdList(): TopArticlesIdRes

    /*
      This function takes article id as parameter and returns ArticlesDetailsRes as result
     */
    suspend fun getTopArticlesDetails(id: Int): ArticlesDetailsRes
}