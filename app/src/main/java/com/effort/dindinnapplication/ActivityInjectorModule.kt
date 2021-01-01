package com.effort.dindinnapplication

import com.effort.dindinnapplication.home.di.MainActivityModule
import com.effort.dindinnapplication.home.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityInjectorModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeInjectorForMainActivity(): MainActivity

}