package com.example.ranosys.autestingdemo.users

import com.example.ranosys.autestingdemo.BasePresenter
import com.example.ranosys.autestingdemo.BaseView
import com.example.ranosys.autestingdemo.data.user.User

/**
 * @author Hitesh Khatri
 */
interface UserContract {

    interface View : BaseView<Presenter> {

        fun setLoadingIndicator(active: Boolean)

        fun showUsers(users:MutableList<User>)

        fun showAddUser()

        fun showAddCar()

        fun showUserDetail(user: User)

        fun showNoTasks()

        fun showLoadingTaskError()

        fun showAllUserDeleted()
    }

    interface Presenter : BasePresenter{

        fun result(requestCode: Int, resultCode: Int)

        fun loadUser(forceUpdate: Boolean)

        fun addNewUser()

        fun openUserDetail(user:User)

        fun clearCompletedTask()

        fun destroy()
    }

}