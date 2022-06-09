package com.nytime.android.articles

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.nytime.android.BR
import com.nytime.android.NyTimesActivity
import com.nytime.android.R
import com.nytime.android.ViewModelProviderFactory
import com.nytime.android.common.BaseFragment
import com.nytime.android.databinding.FragmentArticleDetailsBinding
import javax.inject.Inject

class ArticleDetailsFragment : BaseFragment<FragmentArticleDetailsBinding, ArticleDetailsViewModel>() {
    private var articleDataItem: ArticleDataItem? = null
    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_article_details
    override val viewModel: ArticleDetailsViewModel
        get() = ViewModelProvider(
            this,
            factory
        )[ArticleDetailsViewModel::class.java]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            articleDataItem = arguments?.getParcelable("article")
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        setUpToolbar()
        setArticle()
    }

    private fun setUpToolbar() {
        if (activity != null) {
            (activity as NyTimesActivity).setSupportActionBar(getViewDataBinding().toolbar)
            val actionBar = (activity as NyTimesActivity).supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setDisplayShowHomeEnabled(true)
            actionBar?.setDisplayShowTitleEnabled(false)
        }
        getViewDataBinding().toolbar.setNavigationOnClickListener {
            if (activity != null) {
                activity?.onBackPressed()
            }
        }
    }

    private fun setArticle() {
        if (articleDataItem != null) {
            getViewDataBinding().article = articleDataItem
        }
    }

}

