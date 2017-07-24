package com.example.ranosys.autestingdemo.users

import com.example.ranosys.autestingdemo.BasePresenter
import com.example.ranosys.autestingdemo.BaseView
import com.example.ranosys.autestingdemo.data.car.Car
import com.example.ranosys.autestingdemo.data.user.User

/**
 * Created by hitesh on 24/7/17.
 */
interface UserContract {

    interface View : BaseView<Presenter> {

        fun setLoadingIndicator(active: Boolean)

        fun showUsers(users:List<User>){}

        fun showAddUser()

        fun showAddCar()

        fun showUserDetail(userId:String)

        fun showNoTasks()

        fun showLoadingTaskError()

        fun showAllUserDeleted()


    }

    interface Presenter : BasePresenter{

        fun result(requestCode: Int, resultCode: Int)
        fun loadUser(forceUpdate: Boolean)
        fun addNewUser()
        fun clearCompletedTask()
    }
}