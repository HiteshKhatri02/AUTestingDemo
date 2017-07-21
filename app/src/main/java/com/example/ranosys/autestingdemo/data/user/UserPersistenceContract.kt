package com.example.ranosys.autestingdemo.data.user


/**
 * Created by hitesh on 21/7/17.
 */
object UserPersistenceContract {
    init {
        //user persistence contract
    }

    abstract class UserEntry{
        companion object {
            val TABLE_NAME = "user"
            val _ID = "_id"
            val COLUMN_NAME_ENTRY_ID = "userid"
            val COLUMN_NAME_NAME = "name"
            val COLUMN_NAME_ADDRESS = "address"
            val COLUMN_NAME_CAR_ID = "carIds"
        }
    }
}