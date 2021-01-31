package com.voak.android.developerslife.di.components

import android.content.Context
import com.voak.android.developerslife.di.modules.NetworkModule
import com.voak.android.developerslife.view.base.BaseActivity
import com.voak.android.developerslife.view.base.BaseFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface AppComponent {
    fun provideContext(): Context

    // Activities
    fun inject(baseActivity: BaseActivity)
    fun inject(baseFragment: BaseFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance appContext: Context): AppComponent
    }
}