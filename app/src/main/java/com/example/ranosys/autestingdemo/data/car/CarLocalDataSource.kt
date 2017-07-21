package com.example.ranosys.autestingdemo.data.car

import android.support.annotation.Nullable
import io.reactivex.Observable


/**
 * @author Hitesh Khatri
 */
class CarLocalDataSource :CarDataSource {

    override fun getCars(): Observable<List<Car>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCar(carId: String): Observable<Car> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Nullable
    private var INSTANCE: CarLocalDataSource? = null


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