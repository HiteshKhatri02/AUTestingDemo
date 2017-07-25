package com.example.ranosys.autestingdemo.data.user

import android.util.SparseArray
import android.util.SparseIntArray
import com.example.ranosys.autestingdemo.data.car.Car

/**
 * @author Hitesh Khatri
 */
data class User(
        val id:String,
        val name:String,
        val address:String,
        val carIds:String,
        val carSparseArray:SparseIntArray) {

    override fun toString(): String {
        return "User(id='$id', name='$name', address='$address', carIds='$carIds', carSparseArray=$carSparseArray)"
    }
}