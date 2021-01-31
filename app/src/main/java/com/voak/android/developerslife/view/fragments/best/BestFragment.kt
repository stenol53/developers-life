package com.voak.android.developerslife.view.fragments.best

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.voak.android.developerslife.databinding.FragmentBestBinding
import com.voak.android.developerslife.view.base.BaseFragment

class BestFragment : BaseFragment() {
    private lateinit var binding: FragmentBestBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBestBinding.inflate(inflater, container, false)
        binding.viewModel = ViewModelProvider(this).get(BestViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    companion object {
        fun newInstance(): BestFragment {
            val args = Bundle()
            val fragment = BestFragment()
            fragment.arguments = args

            return fragment
        }
    }
}