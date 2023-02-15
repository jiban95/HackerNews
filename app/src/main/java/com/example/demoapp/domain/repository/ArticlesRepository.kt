package com.example.demoapp.domain.repository

import com.example.demoapp.data.model.ArticlesDetailsRes
import com.example.demoapp.data.model.TopArticlesIdRes

/*
   Interface contains method that are implemented in Repository Implementation class
 */
interface ArticlesRepository {

    suspend fun getTopArticlesIdList(): TopArticlesIdRes

    suspend fun getTopArticlesDetails(id:Int): ArticlesDetailsRes
}