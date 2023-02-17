package com.example.demoapp.presentation.topStoryIdList

/*
 This data class represents state of the api
 */
data class TopStoryDataState(
    val isLoading: Boolean = false,
    val data: ArrayList<Int>? = null,
    val error: String = ""
)
