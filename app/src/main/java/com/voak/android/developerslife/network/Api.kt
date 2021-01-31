package com.voak.android.developerslife.network

import com.voak.android.developerslife.models.Post
import com.voak.android.developerslife.models.PostResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("random?json=true")
    suspend fun getRandomPost(): Response<Post?>

    @GET("latest/{page_id}?json=true")
    suspend fun getLatestPosts(@Path(value = "page_id", encoded = true) page: Int): Response<PostResult?>

    @GET("hot/{page_id}?json=true")
    suspend fun getHottestPosts(@Path(value = "page_id", encoded = true) page: Int): Response<PostResult?>

    @GET("top/{page_id}?json=true")
    suspend fun getBestPosts(@Path(value = "page_id", encoded = true) page: Int): Response<PostResult?>

    companion object {
        const val BASE_URL = "https://developerslife.ru/"
    }
}