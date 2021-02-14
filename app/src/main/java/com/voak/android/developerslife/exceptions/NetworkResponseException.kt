package com.voak.android.developerslife.exceptions

import okhttp3.Request
import okhttp3.Response

open class NetworkResponseException(
        message: String,
        displayedMessage: String? = null,
        response: Response? = null
) : AppException(message, displayedMessage) {
    private val request: Request? = response?.request()
    val httpResponseCode: Int? = response?.code()
    val url: String? = request?.url()?.url()?.path
}