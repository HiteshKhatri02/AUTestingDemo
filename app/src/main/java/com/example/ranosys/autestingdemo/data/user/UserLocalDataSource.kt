package com.example.ranosys.autestingdemo.data.user

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.text.TextUtils
import com.example.ranosys.autestingdemo.data.DbHelper
import com.example.ranosys.autestingdemo.utils.Schedulers.BaseSchedulerProvider
import com.example.ranosys.autestingdemo.utils.Schedulers.JacksonParserUtility
import com.squareup.sqlbrite2.BriteDatabase
import com.squareup.sqlbrite2.SqlBrite
import io.reactivex.Observable
import io.reactivex.functions.Function
import org.jetbrains.annotations.NotNull


/**
 * @author Hitesh Khatri
 */
class UserLocalDataSource(context: Context,
                          baseSchedulerProvider: BaseSchedulerProvider)
    : UserDataSource{


    private var mDatabaseHelper: BriteDatabase?=null

    private var mTaskMapperFunction: Function<Cursor, User>

    init {
        checkNotNull(context,{"context can not be null."})
        checkNotNull(baseSchedulerProvider,{"Scheduler Provider can not be null."})
        val dbhelper:DbHelper =  DbHelper(context)
        val sqlBrite:SqlBrite = SqlBrite.Builder().build()

        mDatabaseHelper = sqlBrite.wrapDatabaseHelper(dbhelper,baseSchedulerProvider.io())

        //Task mapper function
        mTaskMapperFunction = Function<Cursor, User> { this.getUser(it) }

    }

    companion object {

        private var INSTANCE: UserLocalDataSource? = null

        fun getInstance(
                context: Context,
                schedulerProvider: BaseSchedulerProvider): UserLocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = UserLocalDataSource(context, schedulerProvider)
            }
            return INSTANCE as UserLocalDataSource
        }

        fun destroyInstance(){
            INSTANCE=null
        }
    }


    fun getUser(cursor: Cursor):User{
        val itemId=cursor.getString(cursor.getColumnIndexOrThrow(
                UserPersistenceContract.UserEntry.COLUMN_NAME_ENTRY_ID))
        val title=cursor.getString(cursor.getColumnIndexOrThrow(UserPersistenceContract.UserEntry.COLUMN_NAME_NAME))
        val address= cursor.getString(cursor.getColumnIndexOrThrow(UserPersistenceContract.UserEntry.COLUMN_NAME_ADDRESS))
        val carId= cursor.getString(cursor.getColumnIndexOrThrow(UserPersistenceContract.UserEntry.COLUMN_NAME_CAR_ID))
        return User(itemId,title,address,carId)
    }

    override fun getUsers(): Observable<MutableList<User>> {
        val projection =
                arrayOf<String>(
                        UserPersistenceContract.UserEntry.COLUMN_NAME_ENTRY_ID,
                        UserPersistenceContract.UserEntry.COLUMN_NAME_NAME,
                        UserPersistenceContract.UserEntry.COLUMN_NAME_ADDRESS,
                        UserPersistenceContract.UserEntry.COLUMN_NAME_CAR_ID)

        val sql = String.format("SELECT %s FROM %s", TextUtils.join(",", projection),
                UserPersistenceContract.UserEntry.TABLE_NAME)
        return mDatabaseHelper!!.createQuery(
                UserPersistenceContract.UserEntry.TABLE_NAME, sql)
                .mapToList<User>( mTaskMapperFunction)
    }

    override fun getUser(id: String): Observable<User> {
        val projection =
                arrayOf<String>(
                        UserPersistenceContract.UserEntry.COLUMN_NAME_ENTRY_ID,
                        UserPersistenceContract.UserEntry.COLUMN_NAME_NAME,
                        UserPersistenceContract.UserEntry.COLUMN_NAME_ADDRESS,
                        UserPersistenceContract.UserEntry.COLUMN_NAME_CAR_ID)

        val sql = String.format("SELECT %s FROM %s WHERE %s LIKE ?", TextUtils.join(",", projection),
                UserPersistenceContract.UserEntry.TABLE_NAME,UserPersistenceContract.UserEntry.COLUMN_NAME_ENTRY_ID)
        return mDatabaseHelper!!.createQuery(UserPersistenceContract.UserEntry.TABLE_NAME,
                sql).mapToOne(mTaskMapperFunction)

    }

    override fun addUser(user: User) {
        val values=ContentValues()
        values.put(UserPersistenceContract.UserEntry.COLUMN_NAME_ENTRY_ID,user.id)
        values.put(UserPersistenceContract.UserEntry.COLUMN_NAME_NAME,user.name)
        values.put(UserPersistenceContract.UserEntry.COLUMN_NAME_ADDRESS,user.address)
        values.put(UserPersistenceContract.UserEntry.COLUMN_NAME_CAR_ID, user.carIds)
        mDatabaseHelper!!.insert(UserPersistenceContract.UserEntry.TABLE_NAME,
                values,SQLiteDatabase.CONFLICT_REPLACE)
    }

    override fun deleteUser(user: User) {
        deleteUser(user.id)
    }

    override fun deleteUser(userId: String) {
        val selection=UserPersistenceContract.UserEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?"
        val selectionArguments= userId
        mDatabaseHelper!!.delete(UserPersistenceContract.UserEntry.TABLE_NAME
                ,selection, selectionArguments)

    }

    override fun editUser(user: User) {
        val values=ContentValues()
        values.put(UserPersistenceContract.UserEntry.COLUMN_NAME_ENTRY_ID,user.id)
        values.put(UserPersistenceContract.UserEntry.COLUMN_NAME_NAME,user.name)
        values.put(UserPersistenceContract.UserEntry.COLUMN_NAME_ADDRESS,user.address)
        values.put(UserPersistenceContract.UserEntry.COLUMN_NAME_CAR_ID, user.carIds)
        val selection = UserPersistenceContract.UserEntry.COLUMN_NAME_ENTRY_ID + " Like ?"
        mDatabaseHelper!!.update(UserPersistenceContract.UserEntry.TABLE_NAME,values,
                selection,user.id)
    }

    override fun deleteAllUsers() {
        mDatabaseHelper!!.delete(UserPersistenceContract.UserEntry.TABLE_NAME,null)
    }

    override fun refreshUser() {
       //No required
    }

}