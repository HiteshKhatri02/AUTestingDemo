package com.example.ranosys.autestingdemo.data.car

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.annotation.Nullable
import android.text.TextUtils
import com.example.ranosys.autestingdemo.data.DbHelper
import com.example.ranosys.autestingdemo.utils.Schedulers.BaseSchedulerProvider
import com.squareup.sqlbrite2.BriteDatabase
import com.squareup.sqlbrite2.SqlBrite
import io.reactivex.Observable
import io.reactivex.functions.Function


/**
 * @author Hitesh Khatri
 */
class CarLocalDataSource(context: Context,
                         baseSchedulerProvider: BaseSchedulerProvider)
    :CarDataSource {

    override fun getUserCar(carIds: String): Observable<MutableList<Car>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private var mDatabaseHelper: BriteDatabase?=null

    private var mTaskMapperFunction: Function<Cursor, Car>

    companion object {

        private var INSTANCE: CarLocalDataSource? = null

        fun getInstance(
                context: Context,
                schedulerProvider: BaseSchedulerProvider): CarLocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = CarLocalDataSource(context, schedulerProvider)
            }
            return INSTANCE as CarLocalDataSource
        }

        fun destroyInstance(){
            INSTANCE=null
        }
    }

    init {
        checkNotNull(context,{"context can not be null."})
        checkNotNull(baseSchedulerProvider,{"Scheduler Provider can not be null."})
        val dbhelper: DbHelper =  DbHelper(context)
        val sqlBrite: SqlBrite = SqlBrite.Builder().build()

        mDatabaseHelper = sqlBrite.wrapDatabaseHelper(dbhelper,baseSchedulerProvider.io())

        //Task mapper function
        mTaskMapperFunction = Function<Cursor, Car> { this.getCars(it) }

    }

    fun getCars(cursor: Cursor):Car{
        val itemId=cursor.getString(cursor.getColumnIndexOrThrow(
                CarsPersistenceContract.CarEntry.COLUMN_NAME_ENTRY_ID))
        val title =cursor.getString(cursor.getColumnIndexOrThrow(CarsPersistenceContract.CarEntry.COLUMN_NAME_NAME))
        val brand = cursor.getString(cursor.getColumnIndexOrThrow(CarsPersistenceContract.CarEntry.COLUMN_NAME_BRAND))
        val model = cursor.getString(cursor.getColumnIndexOrThrow(CarsPersistenceContract.CarEntry.COLUMN_NAME_MODEL))
        val amount = cursor.getInt(cursor.getColumnIndexOrThrow(CarsPersistenceContract.CarEntry.COLUMN_NAME_AMOUNT))
        val currency = cursor.getString(cursor.getColumnIndexOrThrow(CarsPersistenceContract.CarEntry.COLUMN_NAME_CURRENCY))
              return Car(itemId,title,brand,model,amount,currency)
    }

    override fun getCars(): Observable<List<Car>> {

        val projection= arrayOf(
                CarsPersistenceContract.CarEntry.COLUMN_NAME_ENTRY_ID,
                CarsPersistenceContract.CarEntry.COLUMN_NAME_NAME,
                CarsPersistenceContract.CarEntry.COLUMN_NAME_BRAND,
                CarsPersistenceContract.CarEntry.COLUMN_NAME_MODEL,
                CarsPersistenceContract.CarEntry.COLUMN_NAME_AMOUNT,
                CarsPersistenceContract.CarEntry.COLUMN_NAME_CURRENCY)

        val sql= String.format("SELECT %s FROM %s",
                TextUtils.join(",",projection),
                CarsPersistenceContract.CarEntry.TABLE_NAME)

        return mDatabaseHelper!!.createQuery(
                CarsPersistenceContract.CarEntry.TABLE_NAME,sql)
                .mapToList(mTaskMapperFunction)
    }

    override fun getCar(carId: String): Observable<Car> {
        val projection= arrayOf(
                CarsPersistenceContract.CarEntry.COLUMN_NAME_ENTRY_ID,
                CarsPersistenceContract.CarEntry.COLUMN_NAME_NAME,
                CarsPersistenceContract.CarEntry.COLUMN_NAME_BRAND,
                CarsPersistenceContract.CarEntry.COLUMN_NAME_MODEL,
                CarsPersistenceContract.CarEntry.COLUMN_NAME_AMOUNT,
                CarsPersistenceContract.CarEntry.COLUMN_NAME_CURRENCY)

        val sql= String.format("SELECT %s FROM %s WHERE %s LIKE ?",
                TextUtils.join(",",projection),
                CarsPersistenceContract.CarEntry.TABLE_NAME,
                CarsPersistenceContract.CarEntry.COLUMN_NAME_ENTRY_ID)

        return mDatabaseHelper!!.createQuery(
                CarsPersistenceContract.CarEntry.TABLE_NAME,sql,carId)
                .mapToOne(mTaskMapperFunction)
    }




    override fun addCar(car: Car) {
        val values = ContentValues()
        values.put(CarsPersistenceContract.CarEntry.COLUMN_NAME_ENTRY_ID,car.id)
        values .put(CarsPersistenceContract.CarEntry.COLUMN_NAME_NAME,car.name)
        values.put(CarsPersistenceContract.CarEntry.COLUMN_NAME_BRAND,car.brand)
        values.put(CarsPersistenceContract.CarEntry.COLUMN_NAME_MODEL,car.model)
        values.put(CarsPersistenceContract.CarEntry.COLUMN_NAME_AMOUNT,car.amount)
        values.put(CarsPersistenceContract.CarEntry.COLUMN_NAME_CURRENCY,car.currency)

        mDatabaseHelper!!.insert(
                CarsPersistenceContract.CarEntry.TABLE_NAME,
                values,
                SQLiteDatabase.CONFLICT_REPLACE)
    }

    override fun deleteCar(car: Car) {
        deleteCar(car.id)
    }

    override fun deleteCar(id: String) {
        val selection = CarsPersistenceContract.CarEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?"
        mDatabaseHelper!!.delete(CarsPersistenceContract.CarEntry.TABLE_NAME,selection,id)
    }


    override fun editCar(car: Car) {
        val values = ContentValues()
        values.put(CarsPersistenceContract.CarEntry.COLUMN_NAME_ENTRY_ID,car.id)
        values .put(CarsPersistenceContract.CarEntry.COLUMN_NAME_NAME,car.name)
        values.put(CarsPersistenceContract.CarEntry.COLUMN_NAME_BRAND,car.brand)
        values.put(CarsPersistenceContract.CarEntry.COLUMN_NAME_MODEL,car.model)
        values.put(CarsPersistenceContract.CarEntry.COLUMN_NAME_AMOUNT,car.amount)
        values.put(CarsPersistenceContract.CarEntry.COLUMN_NAME_CURRENCY,car.currency)

        val selection = CarsPersistenceContract.CarEntry.COLUMN_NAME_ENTRY_ID + " Like ?"
        mDatabaseHelper!!.update(CarsPersistenceContract.CarEntry.TABLE_NAME,values,
                selection,car.id)
    }

    override fun purchasedCar(car: Car) {
        val values = ContentValues()

        values.put(CarsPersistenceContract.CarEntry.COLUMN_NAME_ENTRY_ID,car.id)
        values .put(CarsPersistenceContract.CarEntry.COLUMN_NAME_NAME,car.name)
        values.put(CarsPersistenceContract.CarEntry.COLUMN_NAME_BRAND,car.brand)
        values.put(CarsPersistenceContract.CarEntry.COLUMN_NAME_MODEL,car.model)
        values.put(CarsPersistenceContract.CarEntry.COLUMN_NAME_AMOUNT,car.amount)
        values.put(CarsPersistenceContract.CarEntry.COLUMN_NAME_CURRENCY,car.currency)

        val selection = CarsPersistenceContract.CarEntry.COLUMN_NAME_ENTRY_ID + " Like ?"
        mDatabaseHelper!!.update(CarsPersistenceContract.CarEntry.TABLE_NAME,values,
                selection,car.id)
    }

    override fun deleteCars() {
        mDatabaseHelper!!.delete(CarsPersistenceContract.CarEntry.TABLE_NAME,null)
    }

    override fun refreshCars() {
        //No implementation required.
    }

}