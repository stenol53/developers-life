package com.voak.android.developerslife.services

import com.voak.android.developerslife.models.PostResult
import javax.inject.Inject

class BestPostService @Inject constructor(): ApiService() {

    private val loadingStateKey = "bestPostsLoading"
    val loadingState = createAndGetLoadingState(loadingStateKey)

    suspend fun getBestPosts(page: Int): PostResult? {
        return executeOrNull(loadingStateKey) { api.getBestPosts(page) }
    }
}