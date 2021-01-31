package com.voak.android.developerslife.view.fragments.hot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.voak.android.developerslife.databinding.FragmentHotBinding
import com.voak.android.developerslife.view.base.BaseFragment

class HotFragment : BaseFragment() {
    private lateinit var binding: FragmentHotBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHotBinding.inflate(inflater, container, false)
        binding.viewModel = ViewModelProvider(this).get(HotViewModel::class.java)

        return binding.root
    }

    companion object {
        fun newInstance(): HotFragment {
            val args = Bundle()

            val fragment = HotFragment()
            fragment.arguments = args
            return fragment
        }
    }
}