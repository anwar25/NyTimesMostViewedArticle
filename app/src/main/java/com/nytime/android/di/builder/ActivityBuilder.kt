package com.nytime.android.di.builder


import com.nytime.android.NyTimesActivity
import com.nytime.android.articles.ArticleDetailsFragmentProvider
import com.nytime.android.articles.ArticleListFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [ArticleListFragmentProvider::class, ArticleDetailsFragmentProvider::class])
    abstract fun bindNyTimesActivity(): NyTimesActivity
}