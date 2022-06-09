package com.nytime.android.di.module

import android.app.Application
import android.content.Context
import com.nytime.android.BuildConfig
import com.nytime.android.di.ApiInfo
import com.nytime.android.network.ApiService
import com.nytime.android.network.ArticleDataSource
import com.nytime.android.network.repository.ArticleRepository

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @ApiInfo
    fun provideApiKey(): String {
        return BuildConfig.API_KEY
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideArticleDataSource(ArticleRepository: ArticleRepository): ArticleDataSource {
        return ArticleRepository
    }




}