package com.nytime.android.network


import com.nytime.android.network.models.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(ApiEndPoint.ENDPOINT_ARTICLES)
    suspend fun getArticles(
        @Path("period") period: Int,
        @Query("api-key") apiKey: String
    ):ArticlesResponse
}