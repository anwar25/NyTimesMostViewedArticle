package com.nytime.android.common

interface BaseItemListener<T> {
    fun onItemClick(item: T)
    fun onRetryClick()
}