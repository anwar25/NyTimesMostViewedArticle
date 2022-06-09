package com.nytime.android.network


import com.nytime.android.network.models.ArticlesResponse

interface ArticleDataSource {
    suspend fun getArticles(period: Int): Result<ArticlesResponse>
}