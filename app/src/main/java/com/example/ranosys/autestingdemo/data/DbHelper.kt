package com.example.ranosys.autestingdemo.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.ranosys.autestingdemo.data.car.CarsPersistenceContract
import com.example.ranosys.autestingdemo.data.user.UserPersistenceContract
import com.example.ranosys.autestingdemo.utils.AppConstants


/**
 * Created by hitesh on 21/7/17.
 */
class DbHelper(context: Context) : SQLiteOpenHelper(context,
        AppConstants.DATABASE_NAME,
        null,
        AppConstants.DATABASE_VERSION) {


    companion object {

        val TEXT_TYPE = " TEXT"
        val BOOLEAN_TYPE = " INTEGER"
        val COMMA_SEP = ","

        val SQL_CREATE_CAR_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + CarsPersistenceContract.CarEntry.TABLE_NAME + " (" +
                        CarsPersistenceContract.CarEntry._ID + TEXT_TYPE + " PRIMARY KEY," +
                        CarsPersistenceContract.CarEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                        CarsPersistenceContract.CarEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                        CarsPersistenceContract.CarEntry.COLUMN_NAME_BRAND + TEXT_TYPE + COMMA_SEP +
                        CarsPersistenceContract.CarEntry.COLUMN_NAME_MODEL + TEXT_TYPE + COMMA_SEP +
                        CarsPersistenceContract.CarEntry.COLUMN_NAME_AMOUNT + BOOLEAN_TYPE + COMMA_SEP +
                        CarsPersistenceContract.CarEntry.COLUMN_NAME_CURRENCY + TEXT_TYPE + COMMA_SEP +
                        CarsPersistenceContract.CarEntry.COLUMN_NAME_IS_PURCHASED + BOOLEAN_TYPE +
                        " )"

        val SQL_CREATE_USER_ENTRIES=
                "CREATE TABLE IF NOT EXISTS " + UserPersistenceContract.UserEntry.TABLE_NAME + " (" +
                        UserPersistenceContract.UserEntry._ID + TEXT_TYPE + " PRIMARY KEY," +
                        UserPersistenceContract.UserEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                        UserPersistenceContract.UserEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                        UserPersistenceContract.UserEntry.COLUMN_NAME_ADDRESS + TEXT_TYPE + COMMA_SEP +
                        UserPersistenceContract.UserEntry.COLUMN_NAME_CAR_ID + TEXT_TYPE +
                        " )"


    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL(SQL_CREATE_CAR_ENTRIES)
        p0.execSQL(SQL_CREATE_USER_ENTRIES)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }




}