package com.example.ranosys.autestingdemo.utils.validations

/**
 * Created by hitesh on 24/7/17.
 */
object InputValidations{

    fun isEmailValid(email: String): Boolean {
        return email.contains("@")
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length > 4
    }

}