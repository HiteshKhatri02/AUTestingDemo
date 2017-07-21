package com.example.ranosys.autestingdemo.data.car

import rx.Observable

/**
 * @author Hitesh Khatri
 */
interface CarDataSource {

    fun getCars(): Observable<List<Car>>

    fun getCar(carId: String): Observable<Car>

    fun addCar(car: Car)

    fun deleteCar(car: Car)

    fun editCar(car: Car)

    fun purchasedCar(car: Car)

    fun deleteCars()

    fun refreshCars()



}