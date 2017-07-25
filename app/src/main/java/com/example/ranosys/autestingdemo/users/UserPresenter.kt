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
import io.reactivex.functions.Function

/**
 * @author Hitesh Khatri
 */
class UserPresenter(val context:Context,
                    val  userView : UserContract.View,
                    val schedulerProvider : BaseSchedulerProvider ):UserContract.Presenter {



    init {
        checkNotNull(userView, {"userView cannot be null!"})
        checkNotNull(schedulerProvider, {"schedulerProvider cannot be null!"})
        userView.setPresenter(this)
    }

    var userLocalDataSource:UserLocalDataSource?=null
    private var mFirstLoad = true


    override fun start() {
        userLocalDataSource = UserLocalDataSource(context,schedulerProvider )
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

        userLocalDataSource?.getUsers()
                ?.flatMap(object :Function<List<User>, Observable<User>>{
                    override fun apply(t: List<User>): Observable<User> {
                        return Observable.fromIterable(t) }
                })
                ?.toList()
                ?.subscribeOn(schedulerProvider.computation())
                ?.observeOn(schedulerProvider.ui())
                ?.subscribe(object : SingleObserver<MutableList<User>>{
                    override fun onSuccess(t: MutableList<User>) {
                        userView.setLoadingIndicator(false)
                        processTask(t)
                    }

                    override fun onSubscribe(d: Disposable) {

                    }



                    override fun onError(e: Throwable) {
                        userView.showLoadingTaskError()
                    }
                })
    }


    override fun addNewUser() {
        userView.showAddUser()
    }

    override fun clearCompletedTask() {
        userLocalDataSource?.deleteAllUsers()
        userView.showAllUserDeleted()
        loadUsers(false,false)
    }

    fun processTask(users:MutableList<User> ){
        if (users.isEmpty()){
            userView.showNoTasks()
        }else{
            userView.showUsers(users)
        }
    }

    override fun openUserDetail(user:User) {
        userView.showUserDetail(user.id)
    }

    override fun destroy() {
        userLocalDataSource=null
    }

}