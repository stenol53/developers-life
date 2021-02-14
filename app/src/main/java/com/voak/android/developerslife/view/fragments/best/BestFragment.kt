package com.voak.android.developerslife.view.fragments.best

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.voak.android.developerslife.databinding.FragmentBestBinding
import com.voak.android.developerslife.models.Post
import com.voak.android.developerslife.view.base.BaseFragment
import kotlinx.android.synthetic.main.connection_error.view.*
import kotlinx.android.synthetic.main.item_post.view.*

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
        binding.next.setOnClickListener {
            if (checkNetworkConnection()) {
                binding.viewModel?.loadNextPost()
            } else {
                binding.post.isVisible = false
                binding.btnsLayout.isVisible = false
                binding.error.isVisible = true
            }
        }

        binding.previous.setOnClickListener {
            binding.viewModel?.loadPreviousPost()
        }

        binding.error.repeat_btn.setOnClickListener {
            if (checkNetworkConnection()) {
                binding.post.isVisible = true
                binding.btnsLayout.isVisible = true
                binding.error.isVisible = false
                binding.viewModel?.loadNextPost()
            } else {
                binding.post.isVisible = false
                binding.btnsLayout.isVisible = false
                binding.error.isVisible = true
            }
        }

        binding.viewModel?.getLoadingState()?.observe(viewLifecycleOwner) {
            binding.post.post_loader.isVisible = it
            binding.post.image.isVisible = !it
            binding.post.image_title.isVisible = !it
        }

        binding.viewModel?.currentPost?.observe(viewLifecycleOwner) {
            if (!checkNetworkConnection()) {
                binding.error.isVisible = true
            } else {
                onPostLoad(it)
            }
        }

        binding.viewModel?.getCurrentIndex()?.observe(viewLifecycleOwner) {
            binding.previous.isEnabled = it > 0
        }
    }

    private fun onPostLoad(post: Post?) {
        if (post == null) {
            binding.post.isVisible = false
            binding.btnsLayout.isVisible = false
            binding.notFound.isVisible = true
        } else {
            binding.post.isVisible = true
            binding.btnsLayout.isVisible = true
            binding.notFound.isVisible = false
            post.gifURL?.let {
                loadImage(it)
            }
            post.description?.let {
                binding.post.image_title.text = it
            }
        }
    }

    private fun loadImage(url: String) {
        binding.post.post_loader?.isVisible = true
        Glide.with(this)
                .load(url)
                .centerCrop()
                .listener(object: RequestListener<Drawable> {
                    override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                    ): Boolean {
                        binding.post.post_loader?.isVisible = false
                        binding.error.isVisible = true
                        return false
                    }

                    override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                    ): Boolean {
                        binding.post.post_loader.isVisible = false
                        return false
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.post.image)
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