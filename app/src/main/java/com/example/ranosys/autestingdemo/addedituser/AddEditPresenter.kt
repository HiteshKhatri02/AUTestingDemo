package com.example.ranosys.autestingdemo.addedituser

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.View
import com.example.ranosys.autestingdemo.R


import com.example.ranosys.autestingdemo.data.user.User
import com.example.ranosys.autestingdemo.data.user.UserLocalDataSource
import com.example.ranosys.autestingdemo.utils.Schedulers.BaseSchedulerProvider
import com.example.ranosys.autestingdemo.utils.SnackBar
import com.example.ranosys.autestingdemo.utils.validations.InputValidations



/**
 * @author Hitesh Khatri
 */
class AddEditPresenter(val context: Context,
                       val addEditView:AddEditUserContractor.View,
                       val schedulerProvider: BaseSchedulerProvider)
    : AddEditUserContractor.Presenter {


    override fun subscribe() {

    }

    override fun unsubscribe() {

    }


    var userLocalDataSource: UserLocalDataSource?=null

    init {
        checkNotNull(addEditView, {"userView cannot be null!"})
        checkNotNull(schedulerProvider, {"schedulerProvider cannot be null!"})
        addEditView.setPresenter(this)
        userLocalDataSource = UserLocalDataSource(context,schedulerProvider )
    }



    override fun destroy() {
    }




    override fun addUserDetails(user: User, view: View) {
        if (!InputValidations.isUserNameValid(user.id)){

            SnackBar.showSnackBar(view,context.getString(R.string.error_user_name), Color.parseColor("#ff5455"))

        }else if (!InputValidations.isAddressValid(user.address)){

            SnackBar.showSnackBar(view,context.getString(R.string.error_address), Color.parseColor("#ff5455"))

        } else {

            addUser(true,true,user)
        }
    }

    override fun updateUserDetails(user: User, view: View) {
        if (!InputValidations.isUserNameValid(user.id)){

            SnackBar.showSnackBar(view,context.getString(R.string.error_user_name), Color.parseColor("#ff5455"))

        }else if (!InputValidations.isAddressValid(user.address)){

            SnackBar.showSnackBar(view,context.getString(R.string.error_address), Color.parseColor("#ff5455"))

        } else {

            updateUser(true,true,user)
        }
    }


    override fun verifyAddEditUser(intent: Intent) {

        checkNotNull(intent,{"Intent can not be null"})

        if ("ADD_USER".contentEquals(intent.getStringExtra("action"))){

            addEditView.showAsAddUser()

        }else if ("UPDATE_USER".contentEquals(intent.getStringExtra("action"))){

            addEditView.showAsEditTask(intent.getParcelableExtra<User>("useData"))

        }
    }


    fun addUser(forceUpdate: Boolean, showLoader:Boolean, user:User){

        if (showLoader){
            addEditView.setLoadingIndicator(true)
        }

        if (forceUpdate){
            userLocalDataSource!!.refreshUser()
        }

        userLocalDataSource?.addUser(user)
        addEditView.setLoadingIndicator(false)
        addEditView.finishActivity()
    }

    fun updateUser(forceUpdate: Boolean, showLoader:Boolean, user:User){

        if (showLoader){
            addEditView.setLoadingIndicator(true)
        }

        if (forceUpdate){
            userLocalDataSource!!.refreshUser()
        }

        userLocalDataSource?.editUser(user)
        addEditView.setLoadingIndicator(false)
        addEditView.finishActivity()
    }

}