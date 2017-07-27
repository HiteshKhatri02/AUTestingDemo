package com.example.ranosys.autestingdemo.utils.validations

import android.text.TextUtils

/**
 * @author Hitesh Khatri
 */
object InputValidations{

    fun isUserNameValid(name:String) : Boolean{

        return !TextUtils.isDigitsOnly(name) && !TextUtils.isEmpty(name)
    }


    fun isAddressValid(name:String) : Boolean{

        return !TextUtils.isEmpty(name)
    }

}