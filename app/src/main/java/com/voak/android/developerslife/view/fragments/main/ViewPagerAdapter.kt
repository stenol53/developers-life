package com.voak.android.developerslife.view.fragments.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.voak.android.developerslife.view.fragments.best.BestFragment
import com.voak.android.developerslife.view.fragments.hot.HotFragment
import com.voak.android.developerslife.view.fragments.latest.LatestFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> LatestFragment.newInstance()
            1 -> BestFragment.newInstance()
            2 -> HotFragment.newInstance()
            else -> LatestFragment.newInstance()
        }
    }
}