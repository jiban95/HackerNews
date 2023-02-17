package com.example.demoapp.data.remote

import com.example.demoapp.data.model.ArticlesDetailsRes
import com.example.demoapp.data.model.TopArticlesIdRes
import retrofit2.http.GET
import retrofit2.http.Path

/*
   This interface represents api endpoints
 */
interface ApiInterface {

    @GET("topstories.json?print=pretty")
    suspend fun getTopArticlesIdList(): TopArticlesIdRes

    @GET("item/{id}.json?print=pretty")
    suspend fun getTopArticlesDetails(@Path("id") id: Int): ArticlesDetailsRes
}