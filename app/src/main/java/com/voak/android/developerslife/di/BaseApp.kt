package com.voak.android.developerslife.di

import android.app.Application
import com.voak.android.developerslife.BuildConfig
import com.voak.android.developerslife.di.components.AppComponent
import com.voak.android.developerslife.di.components.DaggerAppComponent
import timber.log.Timber

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

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        lateinit var instance: BaseApp
    }
}