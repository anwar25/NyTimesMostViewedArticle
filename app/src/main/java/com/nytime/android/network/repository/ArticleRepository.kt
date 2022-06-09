package com.nytime.android.network.repository


import com.nytime.android.di.ApiInfo
import com.nytime.android.network.ApiService
import com.nytime.android.network.ArticleDataSource
import com.nytime.android.network.Result
import com.nytime.android.network.models.ArticlesResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    private val apiService: ApiService,
    @param:ApiInfo private val apiKey: String
) : ArticleDataSource {
    override suspend fun getArticles(period: Int): Result<ArticlesResponse> {
        return try {
            val articlesResponse = apiService.getArticles(period, apiKey)
            Result.Success(articlesResponse)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }


}


