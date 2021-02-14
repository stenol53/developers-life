package com.voak.android.developerslife.view.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.voak.android.developerslife.R
import com.voak.android.developerslife.databinding.FragmentMainBinding
import com.voak.android.developerslife.view.base.BaseFragment

class MainFragment : BaseFragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        binding.viewPager.adapter = ViewPagerAdapter(this)
        setToolBar(getString(R.string.name), false, binding.root, R.color.white, R.color.black)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = getString(R.string.random)
                1 -> tab.text = getString(R.string.latest)
                2 -> tab.text = getString(R.string.best)
                3 -> tab.text = getString(R.string.hot)
            }
        }.attach()
    }

    companion object {
        fun newInstance(): MainFragment {
            val args = Bundle()

            val fragment = MainFragment()
            fragment.arguments = args
            return fragment
        }
    }
}