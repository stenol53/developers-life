package com.voak.android.developerslife.view.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.voak.android.developerslife.models.Post

abstract class BasePostViewModel : ViewModel() {

    protected val _currentPost: MutableLiveData<Post?> = MutableLiveData<Post?>()
    val currentPost: LiveData<Post?> = _currentPost

    abstract fun loadNextPost()

    abstract fun loadPreviousPost()

    abstract fun getLoadingState(): LiveData<Boolean>?

    abstract fun getCurrentIndex(): LiveData<Int>
}