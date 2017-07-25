package com.example.ranosys.autestingdemo.utils.Schedulers


import android.support.annotation.NonNull
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers


/**
 * @author Hitesh Khatri
 */
class TestSchedulerProvider : BaseSchedulerProvider {

    @NonNull override fun computation(): Scheduler {
        return Schedulers.trampoline()
    }

    @NonNull override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    @NonNull override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }
}