package com.voak.android.developerslife.view.base

import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.voak.android.developerslife.R
import com.voak.android.developerslife.di.BaseApp

abstract class BaseFragment: Fragment() {
    var toolbar: MaterialToolbar? = null

    init {
        BaseApp.instance.component?.inject(this)
    }

    protected fun setToolBar(title: String,
                             needBackBtn: Boolean,
                             view: View,
                             @ColorRes color: Int = R.color.purple_500,
                             @ColorRes textColor: Int = R.color.black
    ) {
        toolbar = view.findViewById(R.id.toolbar)
        toolbar?.background = ContextCompat.getDrawable(requireContext(), color)
        toolbar?.setSubtitleTextColor(ContextCompat.getColor(requireContext(), textColor))
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val actionBar = (activity as AppCompatActivity).actionBar
        val toolbarTitle = toolbar?.findViewById<TextView>(R.id.toolbar_title)
        toolbarTitle?.setTextColor(ContextCompat.getColor(requireContext(), textColor))
        toolbarTitle?.text = title
        if (needBackBtn) {
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setDisplayShowHomeEnabled(true)
            toolbar?.setNavigationOnClickListener { activity?.onBackPressed() }
            actionBar?.setHomeAsUpIndicator(ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_arrow_back_24))
        }
    }

}