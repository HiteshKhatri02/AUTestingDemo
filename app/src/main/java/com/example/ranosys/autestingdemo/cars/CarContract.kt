package com.example.ranosys.autestingdemo.cars

import com.example.ranosys.autestingdemo.BasePresenter
import com.example.ranosys.autestingdemo.BaseView
import com.example.ranosys.autestingdemo.data.car.Car
import com.example.ranosys.autestingdemo.data.user.User

/**
 * @author Hitesh Khatri
 */
interface CarContract {

    private interface View:BaseView<Presenter>{

        fun setLoadingIndicator(active: Boolean)

        fun showCars(cars:List<Car>){}

        fun showAddCar()

        fun showCarDetail(id:String)

    }

    private interface Presenter:BasePresenter{

         fun result(requestCode: Int, resultCode: Int)
    }
}