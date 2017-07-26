package com.example.ranosys.autestingdemo.addedituser

import android.content.Intent
import android.util.SparseIntArray
import com.example.ranosys.autestingdemo.BasePresenter
import com.example.ranosys.autestingdemo.BaseView
import com.example.ranosys.autestingdemo.data.car.Car
import com.example.ranosys.autestingdemo.data.user.User

/**
 * @author Hitesh Khatri
 */
interface AddEditUserContractor {

    interface View:BaseView<Presenter>{

        fun showUser()

        fun showCars(cars:Car, car: SparseIntArray)

        fun setLoadingIndicator(boolean: Boolean)

        fun showAsAddUser()

        fun showAsEditTask(user:User)

        fun showLoadingTaskError()

        fun showNoCars()

        fun showCars(cars:MutableList<Car>)

    }

    interface Presenter:BasePresenter{

        fun destroy()

        fun loadUserCars(forceUpdate:Boolean,
                         showLoader:Boolean,
                         carArray:String)

        fun setCarPurchased(isPurchased:Boolean, key:Int)

        fun verifyAddEditUser(intent:Intent)

        fun addUserDetails(user: User)

        fun updateUserDetails(user:User)

    }
}