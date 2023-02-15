package com.example.demoapp.presentation.storyList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.domain.use_cases.ArticlesDetailsUseCases
import com.example.demoapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StoryDetailsListViewModel @Inject constructor(private val articlesDetailsUseCases: ArticlesDetailsUseCases) :
    ViewModel() {
    private val _articleDetails = MutableStateFlow(StoryDetailsState())
    val articleDetails: StateFlow<StoryDetailsState> = _articleDetails

    fun getNewsData(idList: MutableList<Int>) {
        articlesDetailsUseCases(idList).onEach {
            when (it) {
                is Resource.Loading -> {
                    _articleDetails.value = StoryDetailsState(isLoading = true)
                }
                is Resource.Success -> {
                    _articleDetails.value = StoryDetailsState(data = it.data)
                }
                is Resource.Error -> {
                    _articleDetails.value = StoryDetailsState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }
}