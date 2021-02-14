package com.voak.android.developerslife.view.fragments.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.voak.android.developerslife.view.fragments.best.BestFragment
import com.voak.android.developerslife.view.fragments.hot.HotFragment
import com.voak.android.developerslife.view.fragments.latest.LatestFragment
import com.voak.android.developerslife.view.fragments.random.RandomFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> RandomFragment.newInstance()
            1 -> LatestFragment.newInstance()
            2 -> BestFragment.newInstance()
            3 -> HotFragment.newInstance()
            else -> RandomFragment.newInstance()
        }
    }
}