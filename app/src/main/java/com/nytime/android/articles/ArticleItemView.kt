package com.nytime.android.articles

class ArticleItemView(private val onItemClick: () -> Unit) {
    fun onItemClick() = onItemClick.invoke()
}