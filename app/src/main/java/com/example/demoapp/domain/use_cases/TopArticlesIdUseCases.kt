package com.example.demoapp.domain.use_cases

import android.util.Log
import com.example.demoapp.data.model.TopArticlesIdRes
import com.example.demoapp.domain.repository.ArticlesRepository
import com.example.demoapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * TopArticlesIdUseCases class to get top articles id  data from api
 */

class TopArticlesIdUseCases @Inject constructor(private val articlesRepository: ArticlesRepository) {
    private lateinit var dataList: TopArticlesIdRes

    operator fun invoke(): Flow<Resource<ArrayList<Int>>> =
        flow {
            try {
                emit(Resource.Loading())
                dataList = articlesRepository.getTopArticlesIdList()
                emit(Resource.Success(data = dataList))
            } catch (e: HttpException) {
                emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
            } catch (e: IOException) {
                emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
            } catch (e: Exception) {
                e.localizedMessage?.let { Log.e("error", it) }
            }
        }
}