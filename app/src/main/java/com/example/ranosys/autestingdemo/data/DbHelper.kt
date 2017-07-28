package com.example.ranosys.autestingdemo.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.ranosys.autestingdemo.data.user.UserPersistenceContract
import com.example.ranosys.autestingdemo.utils.AppConstants


/**
 * @author Hitesh Khatri
 */
class DbHelper(context: Context) : SQLiteOpenHelper(context,
        AppConstants.DATABASE_NAME,
        null,
        AppConstants.DATABASE_VERSION) {


    companion object {

        val TEXT_TYPE = " TEXT"
        val COMMA_SEP = ","
        val INT_TYPE = " INTEGER"
        val SQL_CREATE_USER_ENTRIES=
                "CREATE TABLE IF NOT EXISTS " + UserPersistenceContract.UserEntry.TABLE_NAME + " (" +
                        UserPersistenceContract.UserEntry._ID + INT_TYPE + " PRIMARY KEY," +
                        UserPersistenceContract.UserEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                        UserPersistenceContract.UserEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                        UserPersistenceContract.UserEntry.COLUMN_NAME_ADDRESS + TEXT_TYPE +
                        " )"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL(SQL_CREATE_USER_ENTRIES)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}