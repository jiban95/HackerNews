package com.example.demoapp.presentation.storyList

import com.example.demoapp.data.model.ArticlesDetailsRes

/*
 This data class represents state of the api
 */
data class StoryDetailsState(
    val isLoading: Boolean = false,
    val data: List<ArticlesDetailsRes>? = null,
    val error: String = ""
)
