package com.example.ranosys.autestingdemo.data.user


/**
 * @author Hitesh Khatri
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
            val COLUMN_NAME_CAR_IDS = "car_ids"
            val COLUMN_NAME_CAR_MAP = "car_map"
        }
    }
}