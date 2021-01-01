package com.effort.dindinnapplication.home.lifecycle

import androidx.lifecycle.*
import com.effort.dindinnapplication.common.Resource
import com.effort.dindinnapplication.home.data.CartItem
import com.effort.dindinnapplication.home.data.FeedData
import com.effort.dindinnapplication.home.data.FoodItem
import com.effort.dindinnapplication.home.repo.FeedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


typealias FeedResource = Resource<FeedData>

class MainActivityViewModel @Inject constructor(
    private val feedRepository: FeedRepository
) : ViewModel() {

    private val _feedResource: MutableLiveData<FeedResource> = MutableLiveData()

    private val _cart: MutableLiveData<List<CartItem>> = MutableLiveData()

    val cartItemCount: LiveData<Int> = Transformations.map(_cart) { cartItem ->
        cartItem.sumOf { it.count }
    }

    val feedResource: LiveData<FeedResource>
        get() {
            return _feedResource
        }

    val cart: LiveData<List<CartItem>>
        get() {
            return _cart
        }

    fun loadFeed() {
        _feedResource.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val feedData = withContext(Dispatchers.IO) {
                    feedRepository.getFeed()
                }
                _feedResource.value = Resource.Success(feedData)
            } catch (e: Exception) {
                _feedResource.value = Resource.Error(e)
            }
        }
    }

    fun addToCart(foodItem: FoodItem) {
        val cartItems = ArrayList(_cart.value ?: emptyList())
        val itemAlreadyPresentInCart = cartItems.find { it.foodItem == foodItem }
        if (itemAlreadyPresentInCart == null) {
            _cart.value = cartItems + CartItem(foodItem, 1)
        } else {
            _cart.value = cartItems - itemAlreadyPresentInCart + CartItem(
                foodItem,
                itemAlreadyPresentInCart.count + 1
            )
        }
    }

    class Factory @Inject constructor(
        private val feedRepository: FeedRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainActivityViewModel(feedRepository) as T
        }
    }
}