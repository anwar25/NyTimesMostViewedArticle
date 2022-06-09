package com.nytime.android

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nytime.android.common.BaseRecyclerViewAdapter

object BindingUtils {
    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("adapter")
    fun <T> setRecyclerViewData(recyclerView: RecyclerView, items: List<T>?) {
        items?.let {
            (recyclerView.adapter as? BaseRecyclerViewAdapter<T>)?.apply {
                clearItems()
                addItems(items)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("imageUrlCrop")
    fun setImageUrlCrop(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .error(R.drawable.ic_baseline_info_24)
            .into(imageView)
    }



}