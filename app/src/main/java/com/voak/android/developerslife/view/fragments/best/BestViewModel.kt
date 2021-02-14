package com.voak.android.developerslife.view.fragments.best

import com.voak.android.developerslife.di.BaseApp
import com.voak.android.developerslife.repository.BestPostRepository
import com.voak.android.developerslife.view.base.BasePostViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class BestViewModel : BasePostViewModel() {

    @Inject
    lateinit var repository: BestPostRepository

    init {
        BaseApp.instance.component?.inject(this)
        loadNextPost()
    }

    override fun loadNextPost() {
        CoroutineScope(Dispatchers.IO).launch {
            val post = repository.getNextPost()
            _currentPost.postValue(post)
        }
    }

    override fun loadPreviousPost() {
        repository.getPreviousPost()?.let {
            _currentPost.postValue(it)
        }
    }

    override fun getLoadingState() = repository.getLoadingState()

    override fun getCurrentIndex() = repository.currentIndex
}