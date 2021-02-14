package com.voak.android.developerslife.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.voak.android.developerslife.models.Post

abstract class BasePostRepository {

    protected val posts: ArrayList<Post> = ArrayList()
    protected val _currentIndex: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = -1 }
    val currentIndex: LiveData<Int> = _currentIndex
    protected var currentPage = 0

    abstract suspend fun getNextPost(): Post?

    abstract fun getPreviousPost(): Post?

    abstract fun getLoadingState(): LiveData<Boolean>?
}