package com.example.demoapp.presentation.topStoryIdList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.domain.use_cases.TopArticlesIdUseCases
import com.example.demoapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/*
 This viewModel class fetch articlesIdList from api and also manage state of the data
 */
@HiltViewModel
class TopStoryIdListViewModel @Inject constructor(private val useCases: TopArticlesIdUseCases) :
    ViewModel() {

    private val _articleIdList = MutableStateFlow(TopStoryDataState())
    val articleIdList: StateFlow<TopStoryDataState> = _articleIdList

    fun getTopArticlesIdList() {
        useCases().onEach {
            when (it) {
                is Resource.Loading -> {
                    _articleIdList.value = TopStoryDataState(isLoading = true)
                }
                is Resource.Success -> {
                    _articleIdList.value = TopStoryDataState(data = it.data)
                }
                is Resource.Error -> {
                    _articleIdList.value = TopStoryDataState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }
}