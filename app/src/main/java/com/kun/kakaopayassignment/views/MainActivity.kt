package com.kun.kakaopayassignment.views

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.kun.kakaopayassignment.R
import com.kun.kakaopayassignment.databinding.ActivityMainBinding
import com.kun.kakaopayassignment.viewmodels.ListViewModel
import com.kun.kakaopayassignment.views.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int get() = R.layout.activity_main

    override fun doBind() {
        bindingView.apply {
            lifecycleOwner = this@MainActivity

            executePendingBindings()
        }
    }
}