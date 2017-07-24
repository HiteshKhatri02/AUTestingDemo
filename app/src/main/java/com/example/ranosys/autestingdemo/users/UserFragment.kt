package com.example.ranosys.autestingdemo.users

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ranosys.autestingdemo.utils.Schedulers.BaseSchedulerProvider
import com.example.ranosys.autestingdemo.utils.Schedulers.SchedulerProvider

/**
 *@author Hitesh Khatri
 */
class UserFragment : Fragment(),UserContract.View {

    private var userPresenter: UserPresenter? = null

    private var presenter: UserContract.Presenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

userPresenter= UserPresenter(activity,this,
        SchedulerProvider.getInstance() as BaseSchedulerProvider)

        return super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun setLoadingIndicator(active: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showAddUser() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showAddCar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showUserDetail(userId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoadingTaskError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showAllUserDeleted() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPresenter(presenter: UserContract.Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

