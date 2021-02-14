package com.voak.android.developerslife.services

import com.voak.android.developerslife.models.PostResult
import javax.inject.Inject

class HotPostService @Inject constructor(): ApiService() {

    private val loadingStateKey = "hotPostsLoading"
    val loadingState = createAndGetLoadingState(loadingStateKey)

    suspend fun getHotPosts(page: Int): PostResult? {
        return executeOrNull(loadingStateKey) { api.getHottestPosts(page) }
    }
}