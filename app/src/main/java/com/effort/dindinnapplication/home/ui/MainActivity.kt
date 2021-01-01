package com.effort.dindinnapplication.home.ui

import android.app.ActivityOptions
import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.effort.dindinnapplication.R
import com.effort.dindinnapplication.checkout.ui.CheckoutActivity
import com.effort.dindinnapplication.common.BaseActivity
import com.effort.dindinnapplication.common.RecyclerItemClickListener
import com.effort.dindinnapplication.common.Resource
import com.effort.dindinnapplication.common.SpaceItemDecoration
import com.effort.dindinnapplication.databinding.ActivityMainBinding
import com.effort.dindinnapplication.databinding.ItemFeedFoodItemBinding
import com.effort.dindinnapplication.home.data.CartItem
import com.effort.dindinnapplication.home.data.FeedData
import com.effort.dindinnapplication.home.data.FoodItem
import com.effort.dindinnapplication.home.lifecycle.MainActivityViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewmodel: MainActivityViewModel

    @Inject
    lateinit var factory: MainActivityViewModel.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewmodel = viewmodel
        initViews()
        subscribeFeed()
        requestFeed()
    }

    private fun initViews() {
        binding.cart.setOnClickListener {
            val intent = CheckoutActivity.createIntent(
                this@MainActivity,
                viewmodel.cart.value as List<CartItem>
            )
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
    }


    private fun requestFeed() {
        viewmodel.loadFeed()
    }

    private fun subscribeFeed() {
        viewmodel.feedResource.observe(this, {
            when (it) {
                is Resource.Success -> showContent(it.data!!)
                is Resource.Loading -> showLoader()
                is Resource.Error -> showError()
            }
        })
    }

    private fun showError() {

    }

    private fun showLoader() {

    }

    private fun showContent(data: FeedData) {
        binding.rvFeed.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = FeedAdapter(data.foodItems, object : RecyclerItemClickListener<FoodItem> {
                override fun onItemClick(item: FoodItem, position: Int) {
                    viewmodel.addToCart(item)
                }
            })
            addItemDecoration(SpaceItemDecoration(16.dp(resources).toInt()))
        }

        binding.pager.apply {
            adapter = PagerAdapter(this@MainActivity, data.trendingItems)
        }
    }

    class FeedAdapter(
        private val foodItems: List<FoodItem>,
        private val itemClickListener: RecyclerItemClickListener<FoodItem>
    ) : RecyclerView.Adapter<FeedAdapter.FoodItemHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemHolder {
            return FoodItemHolder(
                ItemFeedFoodItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            ).apply {
                binding.price.setOnClickListener {
                    itemClickListener.onItemClick(foodItems[adapterPosition], adapterPosition)
                }
            }
        }

        override fun onBindViewHolder(holder: FoodItemHolder, position: Int) {
            holder.binding.foodItem = foodItems[position]
        }

        override fun getItemCount() = foodItems.size

        class FoodItemHolder(val binding: ItemFeedFoodItemBinding) :
            RecyclerView.ViewHolder(binding.root)

    }

    class PagerAdapter(fragmentActivity: FragmentActivity, private val foodItems: List<FoodItem>) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount() = foodItems.size

        override fun createFragment(position: Int): Fragment =
            TrendingItemFragment.newInstance(foodItems[position])

    }

    private fun Int.dp(resources: Resources): Float =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            resources.displayMetrics
        )

}