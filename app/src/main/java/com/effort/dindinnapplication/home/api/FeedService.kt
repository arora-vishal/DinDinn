package com.effort.dindinnapplication.home.api

import com.effort.dindinnapplication.home.data.FeedData
import com.effort.dindinnapplication.home.data.FoodItem
import javax.inject.Inject

interface FeedService {

    suspend fun getFeed(): FeedData

}

class MockFeedService @Inject constructor() : FeedService {
    override suspend fun getFeed(): FeedData {
        return FeedData(
            trendingItems = listOf(
                FoodItem(
                    name = "CheeseBurger",
                    image = "https://thumbs.dreamstime.com/z/super-saturday-sale-super-saturday-sale-ribbon-discount-porcent-offer-vector-illustration-132460392.jpg",
                    price = 12
                ),
                FoodItem(
                    name = "Pizza",
                    image = "https://static.toiimg.com/thumb/56933159.cms?imgsize=686279&width=800&height=800",
                    price = 20
                ),
                FoodItem(
                    name = "Biryani",
                    image = "https://c.ndtvimg.com/2019-06/s71ihu9_biryani_625x300_05_June_19.jpg",
                    price = 25
                )
            ),
            foodItems = listOf(
                FoodItem(
                    name = "CheeseBurger",
                    image = "https://www.mcdonalds.com/is/image/content/dam/usa/nfl/nutrition/items/hero/desktop/t-mcdonalds-Double-Cheeseburger.jpg",
                    price = 12
                ),
                FoodItem(
                    name = "Pizza",
                    image = "https://static.toiimg.com/thumb/56933159.cms?imgsize=686279&width=800&height=800",
                    price = 20
                ),
                FoodItem(
                    name = "Biryani",
                    image = "https://c.ndtvimg.com/2019-06/s71ihu9_biryani_625x300_05_June_19.jpg",
                    price = 25
                )
            )
        )
    }
}