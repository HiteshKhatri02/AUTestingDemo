package com.example.ranosys.autestingdemo.data.car

import android.provider.BaseColumns



/**
 * Created by hitesh on 19/7/17.
 */
object CarsPersistenceContract {
    init {
// to be safe from accidentally initialisation
    }

    /* Inner class that defines the table contents */
    abstract class CarEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "car"
            val COLUMN_NAME_ENTRY_ID = "entryid"
            val COLUMN_NAME_NAME = "name"
            val COLUMN_NAME_BRAND = "brand"
            val COLUMN_NAME_AMOUNT = "amount"
            val COLUMN_NAME_CURRENCY = "currency"
            val COLUMN_NAME_IS_PURCHASED = "isPurchased"
        }
    }
}// To prevent someone from accidentally instantiating the contract class,
// give it an empty constructor.

