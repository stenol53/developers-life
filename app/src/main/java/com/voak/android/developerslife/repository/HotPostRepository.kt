package com.voak.android.developerslife.repository

import com.voak.android.developerslife.models.Post
import com.voak.android.developerslife.services.HotPostService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HotPostRepository @Inject constructor() : BasePostRepository() {

    @Inject
    lateinit var hotPostService: HotPostService
    override suspend fun getNextPost(): Post? {
        val nextIndex = (_currentIndex.value ?: -1) + 1
        return if (nextIndex >= posts.count()) {
            val posts = hotPostService.getHotPosts(currentPage)
            posts?.result?.let { result ->
                if (result.count() == 0) {
                    return null
                }
                currentPage++
                _currentIndex.postValue(nextIndex)
                this.posts.addAll(result)
                this.posts[nextIndex]
            }
        } else {
            _currentIndex.postValue(nextIndex)
            posts[nextIndex]
        }
    }

    override fun getPreviousPost(): Post? {
        val index = _currentIndex.value ?: -1
        if (index > 0 && posts.count() > index) {
            _currentIndex.postValue(index - 1)
            return posts[index - 1]
        }
        return null
    }

    override fun getLoadingState() = hotPostService.loadingState

}