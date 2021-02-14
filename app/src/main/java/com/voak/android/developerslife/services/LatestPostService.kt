package com.voak.android.developerslife.services

import com.voak.android.developerslife.models.PostResult
import javax.inject.Inject

class LatestPostService @Inject constructor() : ApiService() {

    private val loadingStateKey = "latestPostsLoading"
    val loadingState = createAndGetLoadingState(loadingStateKey)

    suspend fun getLatestPosts(page: Int): PostResult? {
        return executeOrNull(loadingStateKey) { api.getLatestPosts(page) }
    }
}