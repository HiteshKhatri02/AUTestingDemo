package com.example.ranosys.autestingdemo.utils.Schedulers

import rx.Scheduler

/**
 * @author Hiesh khatri
 */
interface BaseSchedulerProvider {

    fun computation(): Scheduler

    fun io():Scheduler

    fun ui():Scheduler

}