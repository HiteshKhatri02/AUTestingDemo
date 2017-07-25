package com.example.ranosys.autestingdemo.dashboard

import com.example.ranosys.autestingdemo.BasePresenter

/**
 * Created by hitesh on 25/7/17.
 */
interface DashboardContractor {

    interface Presenter:BasePresenter{
        fun openUserFragment()
        fun openCarFragment()
    }
}