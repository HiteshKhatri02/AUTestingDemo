package com.example.ranosys.autestingdemo.data.car

import android.support.annotation.Nullable
import rx.Observable

/**
 * @author Hitesh Khatri
 */
class CarLocalDataSource :CarDataSource {

    @Nullable
    private var INSTANCE: CarLocalDataSource? = null

    override fun getTasks(): Observable<List<Car>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTask(taskId: String): Observable<Car> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addCar(car: Car) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteCar(car: Car) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun editCar(car: Car) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun purchasedCar(car: Car) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteCars() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshCars() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}