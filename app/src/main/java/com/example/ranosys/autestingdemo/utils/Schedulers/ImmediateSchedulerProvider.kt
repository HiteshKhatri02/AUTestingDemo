package com.example.ranosys.autestingdemo.utils.Schedulers


import android.support.annotation.NonNull
import rx.Scheduler
import rx.schedulers.Schedulers

/**
 * @author Hitesh Khatri
 */
class ImmediateSchedulerProvider : BaseSchedulerProvider {

    @NonNull override fun computation(): Scheduler {
        return Schedulers.immediate()
    }

    @NonNull override fun io(): Scheduler {
        return Schedulers.immediate()
    }

    @NonNull override fun ui(): Scheduler {
        return Schedulers.immediate()
    }
}