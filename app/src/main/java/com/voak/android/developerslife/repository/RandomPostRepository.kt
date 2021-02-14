package com.voak.android.developerslife.repository

import com.voak.android.developerslife.models.Post
import com.voak.android.developerslife.services.RandomPostService
import javax.inject.Inject

class RandomPostRepository @Inject constructor() : BasePostRepository() {

    @Inject
    lateinit var randomPostService: RandomPostService

    override suspend fun getNextPost(): Post? {
        val post = randomPostService.getRandomPost()
        post?.let {
            posts.add(post)
            val curIndex = _currentIndex.value ?: -1
            _currentIndex.postValue(curIndex + 1)
            return post
        }
        return null
    }

    override fun getPreviousPost(): Post? {
        val curIndex = _currentIndex.value ?: -1
        if (curIndex > 0 && posts.count() > 0) {
            _currentIndex.postValue(curIndex - 1)
            return posts[curIndex - 1]
        }
        return null
    }

    override fun getLoadingState() = randomPostService.loadingState
}