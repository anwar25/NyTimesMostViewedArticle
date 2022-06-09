package com.nytime.android

import androidx.lifecycle.ViewModelProvider
import com.nytime.android.common.BaseActivity
import com.nytime.android.common.EmptyViewModel
import com.nytime.android.databinding.ActivityNyTimesBinding
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class NyTimesActivity : BaseActivity< ActivityNyTimesBinding, EmptyViewModel>()
,HasAndroidInjector{
    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_ny_times

    override val viewModel: EmptyViewModel
        get() = ViewModelProvider(this, factory)[EmptyViewModel::class.java]
}