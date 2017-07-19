package com.example.ranosys.autestingdemo.utils.Schedulers

import android.support.annotation.NonNull
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * @author Hitesh Khatri
 */
class SchedulerProvider() :BaseSchedulerProvider{

    private var INSTANCE: SchedulerProvider? = null

    @Synchronized fun getInstance(): SchedulerProvider? {
        if (INSTANCE == null) {
            INSTANCE = SchedulerProvider()
        }
        return INSTANCE
    }

    @NonNull override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    @NonNull override fun io(): Scheduler {
        return Schedulers.io()
    }

    @NonNull override fun ui(): Scheduler {
     return AndroidSchedulers.mainThread()
    }
}