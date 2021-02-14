package com.voak.android.developerslife.view.main

import android.os.Bundle
import com.voak.android.developerslife.R
import com.voak.android.developerslife.view.base.BaseActivity
import androidx.navigation.fragment.NavHostFragment

class MainActivity : BaseActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun getFragmentContainerId(): Int {
        return R.id.fragment_container
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
    }
}