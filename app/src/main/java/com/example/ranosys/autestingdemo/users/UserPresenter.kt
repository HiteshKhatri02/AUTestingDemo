package com.example.ranosys.autestingdemo.users

import android.content.Context
import android.support.annotation.NonNull
import com.example.ranosys.autestingdemo.data.user.User
import com.example.ranosys.autestingdemo.data.user.UserLocalDataSource
import com.example.ranosys.autestingdemo.utils.Schedulers.BaseSchedulerProvider
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.observers.DisposableObserver

/**
 * @author Hitesh Khatri
 */
class UserPresenter(val context:Context,
                    val  userView : UserContract.View,
                    val schedulerProvider : BaseSchedulerProvider ):UserContract.Presenter {

    private var mFirstLoad = true

    var compositDisposable:CompositeDisposable?=null
    var userLocalDataSource:UserLocalDataSource?=null

    init {
        checkNotNull(userView, {"userView cannot be null!"})
        checkNotNull(schedulerProvider, {"schedulerProvider cannot be null!"})
        userLocalDataSource = UserLocalDataSource(context,schedulerProvider )
        compositDisposable = CompositeDisposable()
        userView.setPresenter(this)
    }

    override fun subscribe() {
        loadUser(false)
    }

    override fun unsubscribe() {
        compositDisposable?.clear()
    }

    override fun result(requestCode: Int, resultCode: Int) {

    }

    override fun loadUser(forceUpdate: Boolean) {
        loadUsers(forceUpdate||mFirstLoad, true)
    }

    fun loadUsers(forceUpdate: Boolean, showLoader:Boolean){
        if (showLoader){
            userView.setLoadingIndicator(true)
        }

        if (forceUpdate){
            userLocalDataSource!!.refreshUser()
        }

        compositDisposable?.clear()

        val dis:Disposable?=userLocalDataSource?.getUsers()
                ?.subscribeOn(schedulerProvider.computation())
                ?.observeOn(schedulerProvider.ui())
                ?.subscribeWith(object : DisposableObserver<MutableList<User>>() {
                    override fun onError(e: Throwable) {
                        userView.showLoadingTaskError()
                    }

                    override fun onComplete() {
                        userView.setLoadingIndicator(false)
                    }

                    override fun onNext(t: MutableList<User>) {
                        processUser(t)
                        userView.setLoadingIndicator(false)
                    }

                })
        compositDisposable?.add(dis!!)
    }


    override fun addNewUser() {
        userView.showAddUser()
    }

    override fun clearCompletedTask() {
        userLocalDataSource?.deleteAllUsers()
        userView.showAllUserDeleted()
        loadUsers(false,false)
    }

    fun processUser(users:MutableList<User> ){
        if (users.isEmpty()){
            userView.showNoTasks()
        }else{
            userView.showUsers(users)
        }
    }

    override fun openUserDetail(user:User) {
        userView.showUserDetail(user)
    }

    override fun destroy() {
        userLocalDataSource=null
    }

}