package com.voak.android.developerslife.di

import android.app.Application
import com.voak.android.developerslife.di.components.AppComponent
import com.voak.android.developerslife.di.components.DaggerAppComponent

class BaseApp : Application() {
    var component: AppComponent? = null
    get() {
        if (field == null) {
            field = DaggerAppComponent.factory().create(this)
        }
        return field
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: BaseApp
    }
}