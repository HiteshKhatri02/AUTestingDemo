package com.example.ranosys.autestingdemo.addedituser

import android.content.Context
import android.util.SparseIntArray
import com.example.ranosys.autestingdemo.data.user.User
import com.example.ranosys.autestingdemo.data.user.UserLocalDataSource
import com.example.ranosys.autestingdemo.utils.Schedulers.BaseSchedulerProvider

/**
 * @author Hitesh Khatri
 */
class AddEditPresenter(val context: Context,
                       val addEditView:AddEditUserContractor.View,
                       val schedulerProvider: BaseSchedulerProvider)
    : AddEditUserContractor.Presenter {


    private var userLocalDataStorage:UserLocalDataSource?=null

    init {
        checkNotNull(addEditView, {"userView cannot be null!"})
        checkNotNull(schedulerProvider, {"schedulerProvider cannot be null!"})
        addEditView.setPresenter(this)
    }

    override fun start() {
        userLocalDataStorage = UserLocalDataSource(context, schedulerProvider )
    }

    override fun destroy() {
       userLocalDataStorage = null
    }


    override fun setCarPurchased(isPurchased: Boolean, key: Int) {

    }

    override fun addUserDetails(user: User) {

    }

    override fun updateUserDetails(user: User) {

    }

    override fun loadUserCars(forceUpdate: Boolean, showLoader: Boolean, carArray: SparseIntArray) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}