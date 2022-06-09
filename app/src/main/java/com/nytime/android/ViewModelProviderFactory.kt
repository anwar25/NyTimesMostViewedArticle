package com.nytime.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.nytime.android.articles.ArticleDetailsViewModel
import com.nytime.android.articles.ArticleListViewModel
import com.nytime.android.common.BaseViewModel
import com.nytime.android.common.EmptyViewModel
import com.nytime.android.network.ArticleDataSource

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory @Inject constructor(
    private val articleDataSource: ArticleDataSource,
) : NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(EmptyViewModel::class.java) -> {
                EmptyViewModel() as T
            }
            modelClass.isAssignableFrom(ArticleListViewModel::class.java) -> {
                ArticleListViewModel(articleDataSource) as T
            }
            modelClass.isAssignableFrom(ArticleDetailsViewModel::class.java) -> {
                ArticleDetailsViewModel() as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}