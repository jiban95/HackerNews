package com.example.demoapp.presentation.storyList

import com.example.demoapp.data.model.ArticlesDetailsRes

data class StoryDetailsState(
    val isLoading: Boolean = false,
    val data: List<ArticlesDetailsRes>? = null,
    val error: String = ""
)
