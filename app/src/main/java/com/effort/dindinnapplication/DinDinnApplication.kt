package com.effort.dindinnapplication

import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class DinDinnApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.factory().create(this).inject(this)
    }

    override fun androidInjector() = androidInjector
}