package com.effort.dindinnapplication.home.repo

import com.effort.dindinnapplication.home.api.FeedService
import com.effort.dindinnapplication.home.data.FeedData
import javax.inject.Inject

interface FeedRepository {

    suspend fun getFeed(): FeedData
}

class FeedRepositoryImpl @Inject constructor(
    private val feedService: FeedService
) : FeedRepository {
    override suspend fun getFeed(): FeedData {
        return feedService.getFeed()
    }
}