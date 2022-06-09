package com.nytime.android.articles

class ArticleEmptyItemView(private val onRetry: () -> Unit) {
    fun onRetryClick() = onRetry.invoke()
}