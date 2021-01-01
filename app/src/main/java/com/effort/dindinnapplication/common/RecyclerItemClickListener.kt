package com.effort.dindinnapplication.common

interface RecyclerItemClickListener<T> {

    fun onItemClick(item: T, position: Int)
}