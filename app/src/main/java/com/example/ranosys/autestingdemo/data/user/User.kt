package com.example.ranosys.autestingdemo.data.user

import com.example.ranosys.autestingdemo.data.car.Car

/**
 * @author Hitesh Khatri
 */
data class User(
        val id:String,
        val name:String,
        val address:String,
        val carIds:String) {

    override fun toString(): String {
        return "User(id='$id', name='$name', address='$address', carIds=$carIds)"
    }
}