package com.example.demoapp.domain.use_cases

import android.util.Log
import com.example.demoapp.data.model.ArticlesDetailsRes
import com.example.demoapp.domain.repository.ArticlesRepository
import com.example.demoapp.utils.Constants
import com.example.demoapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * ArticlesDetailsUseCases class to get top articles details  data from api
 */

class ArticlesDetailsUseCases @Inject constructor(private val articlesRepository: ArticlesRepository) {
    private lateinit var data: MutableList<ArticlesDetailsRes>

    operator fun invoke(idList: MutableList<Int>): Flow<Resource<List<ArticlesDetailsRes>>> = flow {
        try {

            data = ArrayList()
            data.clear()

            idList.forEach { id ->
                data.add(articlesRepository.getTopArticlesDetails(id))
            }
            emit(Resource.Success(data = data))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: Constants.unknownError))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: Constants.no_internet))
        } catch (e: Exception) {
            e.localizedMessage?.let { emit(Resource.Error(message = it)) }
        }
    }
}