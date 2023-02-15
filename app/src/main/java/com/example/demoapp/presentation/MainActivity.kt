package com.example.demoapp.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import com.example.demoapp.data.model.ArticlesDetailsRes
import com.example.demoapp.databinding.ActivityMainBinding

import com.example.demoapp.presentation.storyDetails.StoryDetailsActivity
import com.example.demoapp.presentation.storyList.StoryDetailsListViewModel
import com.example.demoapp.presentation.topStoryIdList.TopStoryIdListViewModel
import com.example.demoapp.presentation.topStoryIdList.TopStoryListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), TopStoryListAdapter.AdapterItemClick {
    private val topStoryIdViewModel: TopStoryIdListViewModel by viewModels()
    private val storyDetailsViewModel: StoryDetailsListViewModel by viewModels()
    private var binding: ActivityMainBinding? = null
    private var idList: MutableList<Int> = ArrayList()
    private var idListCopy: MutableList<Int> = ArrayList()
    private var articleDetailsList: MutableList<ArticlesDetailsRes> = ArrayList()
    private var pageCount = 1
    private var dataCountStart = 0
    private var dataCountEnd = 10
    private var eachDataSet = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        topStoryIdViewModel.getNewsData()
        lifecycle.coroutineScope.launchWhenStarted {
            topStoryIdViewModel.articleIdList.collect {

                if (it.isLoading) {
                    binding!!.loader.visibility = View.VISIBLE
                }

                if (it.error.isNotEmpty()) {
                    binding!!.loader.visibility = View.INVISIBLE
                    Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                }

                it.data?.let { it1 ->
                    if (it1.isNotEmpty()) {
                        idList = it.data
                        getIdData()
                    }
                }
            }
        }

        binding!!.nScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

            if (scrollY == binding!!.nScrollView.getChildAt(0).measuredHeight - v.measuredHeight) {
                pageCount++
                binding!!.loaderMore.visibility = View.VISIBLE
                if (pageCount < 50) {
                    getIdData()
                }
            }
        }
    }

    private fun getIdData() {
        if (idList.size > 0) {
            idListCopy.clear()
            for (i in dataCountStart until dataCountEnd - 1) {
                idListCopy.add(idList[i])
            }

            dataCountStart = dataCountEnd
            dataCountEnd = pageCount * eachDataSet

            storyDetailsViewModel.getNewsData(idListCopy) // call the method of viewModel to call topStoryDetails api

            lifecycle.coroutineScope.launchWhenCreated {
                storyDetailsViewModel.articleDetails.collect {

                    if (it.error.isNotEmpty()) {
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                    }

                    it.data?.let { data ->
                        data.forEach { i ->
                            articleDetailsList.add(i)
                        }
                        val topStoryListAdapter =
                            TopStoryListAdapter(this@MainActivity, articleDetailsList)
                        binding!!.mRecycler.adapter = topStoryListAdapter
                        binding!!.loader.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    override fun onItemClick(url: String) {
        startActivity(Intent(this, StoryDetailsActivity::class.java).putExtra("url", url))
    }
}