package com.example.ranosys.autestingdemo.addedituser

import android.content.Intent

import com.example.ranosys.autestingdemo.BasePresenter
import com.example.ranosys.autestingdemo.BaseView
import com.example.ranosys.autestingdemo.data.user.User

/**
 * @author Hitesh Khatri
 */
interface AddEditUserContractor {

    interface View:BaseView<Presenter>{

        fun setLoadingIndicator(boolean: Boolean)

        fun showAsAddUser()

        fun showAsEditTask(user:User)

        fun showLoadingTaskError()

        fun finishActivity()


    }

    interface Presenter:BasePresenter{

        fun destroy()

        fun verifyAddEditUser(intent:Intent)

        fun addUserDetails(user: User, view:android.view.View)

        fun updateUserDetails(user:User , view:android.view.View)

    }
}