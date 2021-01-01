package com.effort.dindinnapplication.home.data

import android.os.Parcel
import android.os.Parcelable

data class CartItem(
    val foodItem: FoodItem,
    val count: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(FoodItem::class.java.classLoader)!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(foodItem, flags)
        parcel.writeInt(count)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CartItem> {
        override fun createFromParcel(parcel: Parcel): CartItem {
            return CartItem(parcel)
        }

        override fun newArray(size: Int): Array<CartItem?> {
            return arrayOfNulls(size)
        }
    }
}
