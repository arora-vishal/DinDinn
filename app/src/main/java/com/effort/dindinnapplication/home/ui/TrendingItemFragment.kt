package com.effort.dindinnapplication.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.effort.dindinnapplication.databinding.FragmentTrendingItemBinding
import com.effort.dindinnapplication.home.data.FoodItem

class TrendingItemFragment : Fragment() {

    private lateinit var binding: FragmentTrendingItemBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrendingItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.foodItem = arguments?.getParcelable<FoodItem>(KEY_FOOD_ITEM) as FoodItem
    }

    companion object {

        val TAG = TrendingItemFragment::class.java.canonicalName as String

        private const val KEY_FOOD_ITEM = "KEY_FOOD_ITEM"

        fun newInstance(foodItem: FoodItem): TrendingItemFragment {
            return TrendingItemFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_FOOD_ITEM, foodItem)
                }
            }
        }
    }
}