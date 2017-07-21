package com.example.ranosys.autestingdemo.data.user

import android.content.Context
import android.database.Cursor
import com.example.ranosys.autestingdemo.data.DbHelper
import com.example.ranosys.autestingdemo.utils.Schedulers.BaseSchedulerProvider
import com.example.ranosys.autestingdemo.utils.Schedulers.JacksonParserUtility
import com.squareup.sqlbrite2.BriteDatabase
import com.squareup.sqlbrite2.SqlBrite
import io.reactivex.Observable
import io.reactivex.functions.Function


/**
 * @author Hitesh Khatri
 */
class UserLocalDataSource(context: Context,
                          baseSchedulerProvider: BaseSchedulerProvider)
    : UserDataSource{




    private var mDatabaseHelper: BriteDatabase?=null

    private var mTaskMapperFunction: Function<Cursor, User>?=null

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
        val carId=JacksonParserUtility.convertCarIdsStringToArray(
                cursor.getString(cursor.getColumnIndexOrThrow(UserPersistenceContract.UserEntry.COLUMN_NAME_CAR_ID))
        )

        return User(itemId,title,address,carId)
    }

    override fun getUsers(): Observable<List<User>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUser(id: String): Observable<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addUser(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteUser(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun editUser(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun purchasedUser(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteUser() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshUser() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}