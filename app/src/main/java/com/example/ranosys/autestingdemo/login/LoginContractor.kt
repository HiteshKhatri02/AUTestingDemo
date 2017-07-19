package com.example.ranosys.autestingdemo.login

import com.example.ranosys.autestingdemo.BasePresenter
import com.example.ranosys.autestingdemo.BaseView

/**
 * @author Htesh Khatri
 */
interface LoginContractor {

    interface View : BaseView<Presenter>{

        fun setLoadingIndicator(active: Boolean)

        fun showAlert(message:String)

    }

    interface Presenter : BasePresenter {

    }
}