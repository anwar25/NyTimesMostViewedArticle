package com.nytime.android.common

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T>(
    val items: MutableList<T>,
    val itemListener: BaseItemListener<T>
) :
    RecyclerView.Adapter<BaseViewHolder>() {

    val VIEW_TYPE_EMPTY = 0
    val VIEW_TYPE_NORMAL = 1

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addItems(items: List<T>?) {
        items?.let { this.items.addAll(it) }
        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
    }

}