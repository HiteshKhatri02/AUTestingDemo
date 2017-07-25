package com.example.ranosys.autestingdemo.data.car

import io.reactivex.Observable


/**
 * @author Hitesh Khatri
 */
interface CarDataSource {

    fun getCars(): Observable<List<Car>>

    fun getCar(carId: String): Observable<Car>

    fun getUserCar(carIds:String) : Observable<MutableList<Car>>

    fun addCar(car: Car)

    fun deleteCar(car: Car)

    fun deleteCar(id:String)

    fun editCar(car: Car)

    fun purchasedCar(car: Car)

    fun deleteCars()

    fun refreshCars()



}