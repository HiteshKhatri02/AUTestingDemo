package com.example.ranosys.autestingdemo.utils.Schedulers

import io.reactivex.Scheduler


/**
 * @author Hiesh khatri
 */
interface BaseSchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui():Scheduler

}