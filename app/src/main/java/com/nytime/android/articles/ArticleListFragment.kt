package com.nytime.android.articles

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.nytime.android.BR
import com.nytime.android.NyTimesActivity
import com.nytime.android.R
import com.nytime.android.ViewModelProviderFactory
import com.nytime.android.common.BaseFragment
import com.nytime.android.common.NavigationCommand
import com.nytime.android.databinding.FragmentArticleListBinding
import javax.inject.Inject

class ArticleListFragment : BaseFragment<FragmentArticleListBinding,ArticleListViewModel>()
,ArticleAdapterListener{

    @Inject
    lateinit var factory: ViewModelProviderFactory
    private lateinit var articleAdapter: ArticleAdapter

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_article_list
    override val viewModel: ArticleListViewModel
        get() = ViewModelProvider(
            this,
            factory
        )[ArticleListViewModel::class.java]


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        articleAdapter = ArticleAdapter(arrayListOf(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        if (activity != null) (activity as NyTimesActivity).setSupportActionBar(
            getViewDataBinding().toolbar
        )
        setHasOptionsMenu(true)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        getViewDataBinding().resultsBeanRecyclerView.layoutManager = LinearLayoutManager(activity)
        getViewDataBinding().resultsBeanRecyclerView.itemAnimator = DefaultItemAnimator()
        getViewDataBinding().resultsBeanRecyclerView.adapter = articleAdapter
    }

    override fun onItemClick(item: ArticleDataItem) {
        navigate(
            NavigationCommand.To(
                ArticleListFragmentDirections.toArticleDetailsFragment(item)
            )
        )
    }

    override fun onCreateOptionsMenu(
        menu: Menu,
        inflater: MenuInflater
    ) {
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onRetryClick() {
        viewModel.fetchArticles(7)
    }
}