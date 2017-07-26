package com.example.ranosys.autestingdemo.addedituser

import android.content.Context
import android.content.Intent
import android.util.SparseBooleanArray
import com.example.ranosys.autestingdemo.data.car.Car
import com.example.ranosys.autestingdemo.data.car.CarLocalDataSource
import com.example.ranosys.autestingdemo.data.user.User
import com.example.ranosys.autestingdemo.utils.Schedulers.BaseSchedulerProvider
import io.reactivex.Observable
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function

/**
 * @author Hitesh Khatri
 */
class AddEditPresenter(val context: Context,
                       val addEditView:AddEditUserContractor.View,
                       val schedulerProvider: BaseSchedulerProvider)
    : AddEditUserContractor.Presenter {



    private var carLocalDataStorage:CarLocalDataSource?=null

    init {
        checkNotNull(addEditView, {"userView cannot be null!"})
        checkNotNull(schedulerProvider, {"schedulerProvider cannot be null!"})
        addEditView.setPresenter(this)
    }

    override fun start() {
        carLocalDataStorage = CarLocalDataSource(context, schedulerProvider )
    }

    override fun destroy() {
        carLocalDataStorage = null
    }


    override fun setCarPurchased(isPurchased: Boolean, key: Int) {

    }

    override fun addUserDetails(user: User) {

    }

    override fun updateUserDetails(user: User) {

    }

    override fun loadUserCars(forceUpdate: Boolean, showLoader: Boolean, carArray:String) {

        if (showLoader){
            addEditView.setLoadingIndicator(true)
        }

        if (forceUpdate){
            carLocalDataStorage!!.refreshCars()
        }

        carLocalDataStorage?.getUserCar(carArray)
                ?.flatMap(object : Function<MutableList<Car>, Observable<Car>> {
                    override fun apply(t: MutableList<Car>): Observable<Car> {
                        return Observable.fromIterable(t) }
                })
                ?.toList()
                ?.subscribeOn(schedulerProvider.computation())
                ?.observeOn(schedulerProvider.ui())
                ?.subscribe(object : SingleObserver<MutableList<Car>> {

                    override fun onSuccess(t: MutableList<Car>) {
                        addEditView.setLoadingIndicator(false)
                        processCars(t)
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onError(e: Throwable) {
                        addEditView.showLoadingTaskError()
                    }
                })
    }

    fun processCars(cars:MutableList<Car> ){
        if (cars.isEmpty()){
            addEditView.showNoCars()
        }else{
            addEditView.showCars(cars)
        }
    }

    override fun verifyAddEditUser(intent: Intent) {
       checkNotNull(intent,{"Intent can not be null"})

        if ("ADD_USER".contentEquals(intent.getStringExtra("action"))){
            addEditView.showAsAddUser()
        }else{
            addEditView.showAsEditTask(intent.getParcelableExtra<User>())
        }
    }


}