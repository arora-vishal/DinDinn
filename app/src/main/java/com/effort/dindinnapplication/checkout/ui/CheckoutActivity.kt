package com.effort.dindinnapplication.checkout.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.effort.dindinnapplication.R
import com.effort.dindinnapplication.common.BaseActivity
import com.effort.dindinnapplication.databinding.ActivityCheckoutBinding
import com.effort.dindinnapplication.databinding.ItemCartBinding
import com.effort.dindinnapplication.home.data.CartItem

class CheckoutActivity : BaseActivity() {

    private lateinit var binding: ActivityCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_checkout)
        binding.toolbar.setNavigationOnClickListener {
            finishAfterTransition()
        }
        renderCartItems(intent.getParcelableArrayListExtra<CartItem>(KEY_CART_ITEMS) as List<CartItem>)
    }

    private fun renderCartItems(cartItems: List<CartItem>) {
        binding.rvCartItems.apply {
            layoutManager =
                LinearLayoutManager(this@CheckoutActivity, LinearLayoutManager.VERTICAL, false)
            adapter = CartItemsAdapter(cartItems)
            addItemDecoration(
                DividerItemDecoration(
                    this@CheckoutActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAfterTransition()
    }

    class CartItemsAdapter(
        private val cartItems: List<CartItem>
    ) : RecyclerView.Adapter<CartItemsAdapter.CartItemViewHolder>() {

        class CartItemViewHolder(val binding: ItemCartBinding) :
            RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
            return CartItemViewHolder(
                ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }

        override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
            holder.binding.cartItem = cartItems[position]
        }

        override fun getItemCount() = cartItems.size
    }

    companion object {

        private const val KEY_CART_ITEMS = "KEY_CART_ITEMS"

        fun createIntent(context: Context, cartItems: List<CartItem>): Intent {
            return Intent(context, CheckoutActivity::class.java).apply {
                putParcelableArrayListExtra(KEY_CART_ITEMS, ArrayList(cartItems))
            }
        }
    }

}