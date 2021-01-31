package com.voak.android.developerslife.view.fragments.latest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.voak.android.developerslife.databinding.FragmentLatestBinding
import com.voak.android.developerslife.view.base.BaseFragment

class LatestFragment : BaseFragment() {
    private lateinit var binding: FragmentLatestBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLatestBinding.inflate(inflater, container, false)
        binding.viewModel = ViewModelProvider(this).get(LatestViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    companion object {
        fun newInstance(): LatestFragment {
            val args = Bundle()

            val fragment = LatestFragment()
            fragment.arguments = args
            return fragment
        }
    }
}