package com.voak.android.developerslife.view.base

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.voak.android.developerslife.R
import com.voak.android.developerslife.di.BaseApp

abstract class BaseActivity: AppCompatActivity() {
    @LayoutRes
    protected open fun getLayoutRes(): Int = R.layout.activity_main

    @IdRes
    protected open fun getFragmentContainerId(): Int = R.id.fragment_container

    init {
        BaseApp.instance.component?.inject(this)
    }

}