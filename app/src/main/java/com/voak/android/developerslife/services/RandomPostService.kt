package com.voak.android.developerslife.services

import com.voak.android.developerslife.models.Post
import javax.inject.Inject

class RandomPostService @Inject constructor() : ApiService() {

    private val loadingStateKey = "loadingRandomPost"
    val loadingState = createAndGetLoadingState(loadingStateKey)

    suspend fun getRandomPost() : Post? {
        return executeOrNull(loadingStateKey) { api.getRandomPost() }
    }
}