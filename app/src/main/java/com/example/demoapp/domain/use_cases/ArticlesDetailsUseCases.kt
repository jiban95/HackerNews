package com.example.demoapp.domain.use_cases

import android.util.Log
import com.example.demoapp.data.model.ArticlesDetailsRes
import com.example.demoapp.domain.repository.ArticlesRepository
import com.example.demoapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * ArticlesDetailsUseCases class to get top articles id  data from api
 */

class ArticlesDetailsUseCases @Inject constructor(private val articlesRepository: ArticlesRepository) {
    private var data: MutableList<ArticlesDetailsRes> = ArrayList()

    operator fun invoke(idList: MutableList<Int>): Flow<Resource<List<ArticlesDetailsRes>>> = flow {
        try {
            emit(Resource.Loading())
            idList.forEach { id ->
                data.add(articlesRepository.getTopArticlesDetails(id))
            }
            emit(Resource.Success(data = data))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: e.localizedMessage))
            e.localizedMessage?.let { Log.e("error", it) }
        }
    }
}