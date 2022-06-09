package com.nytime.android.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nytime.android.common.BaseViewModel
import com.nytime.android.network.ArticleDataSource
import com.nytime.android.network.Result
import com.nytime.android.network.models.ArticlesResponse
import kotlinx.coroutines.launch

class ArticleListViewModel(
    private val articleDataSource: ArticleDataSource
) : BaseViewModel() {
    private val articleListLiveData: MutableLiveData<List<ArticleDataItem>> = MutableLiveData()

    fun fetchArticles(period: Int) {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = articleDataSource.getArticles(period)) {
                is Result.Success<ArticlesResponse> -> {
                    result.data.articles?.let { mapArticlesDataItem(it) }
                    isLoading.value = false
                }
                is Result.Error -> {
                    isLoading.value = false
                    showToast.value = result.message
                }
            }
        }
    }

    val articlesLiveDataLiveData: LiveData<List<ArticleDataItem>>
        get() = articleListLiveData

    init {
        fetchArticles(7)
    }

    private fun mapArticlesDataItem(articles: List<ArticlesResponse.Article>) {
        articleListLiveData.value = articles.map {
            ArticleDataItem(
                it.id,
                if (!it.media.isNullOrEmpty()) it.media?.get(0)?.mediaMetaData?.get(2)?.url else "",
                it.title,
                it.byline,
                it.abstractX,
                it.publishedDate,
                it.url,
                if (!it.media.isNullOrEmpty()) it.media?.get(0)?.mediaMetaData?.get(1)?.url else ""
            )
        }
    }
}