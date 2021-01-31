package com.voak.android.developerslife.models

import com.google.gson.annotations.SerializedName

data class PostResult(
    @SerializedName("result")
    val result: List<Post>? = null,
    @SerializedName("totalCount")
    val totalCount: Int? = null
)