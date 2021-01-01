package com.effort.dindinnapplication.home.di

import com.effort.dindinnapplication.home.api.FeedService
import com.effort.dindinnapplication.home.api.MockFeedService
import com.effort.dindinnapplication.home.repo.FeedRepository
import com.effort.dindinnapplication.home.repo.FeedRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun bindFeedRepository(feedRepositoryImpl: FeedRepositoryImpl): FeedRepository

    @Binds
    abstract fun bindFeedService(feedService: MockFeedService): FeedService
}