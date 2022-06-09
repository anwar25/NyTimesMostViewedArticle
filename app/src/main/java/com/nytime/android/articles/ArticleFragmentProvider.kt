package com.nytime.android.articles

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ArticleListFragmentProvider {
    @ContributesAndroidInjector
    abstract fun provideArticleFragmentFactory(): ArticleListFragment
}