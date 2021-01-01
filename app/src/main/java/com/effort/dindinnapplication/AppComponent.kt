package com.effort.dindinnapplication

import android.app.Application
import com.effort.dindinnapplication.home.di.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [MainActivityModule::class, AndroidSupportInjectionModule::class, ActivityInjectorModule::class])
interface AppComponent {

    fun inject(application: DinDinnApplication)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}