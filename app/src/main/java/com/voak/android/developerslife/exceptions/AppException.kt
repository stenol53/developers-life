package com.voak.android.developerslife.exceptions

import com.voak.android.developerslife.utils.AppContext

open class AppException(
        message: String,
        override val displayedMessage: String? = null
): Exception(message), DisplayedMessageExceptionInterface {
    constructor(message: String, displayedMessage: Int? = null): this(message, AppContext.getString(displayedMessage))
}