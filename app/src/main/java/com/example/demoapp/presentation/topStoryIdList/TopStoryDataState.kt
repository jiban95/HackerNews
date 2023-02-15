package com.example.demoapp.presentation.topStoryIdList

data class TopStoryDataState(
    val isLoading: Boolean = false,
    val data: ArrayList<Int>? = null,
    val error: String = ""
)
