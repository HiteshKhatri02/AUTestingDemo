package com.example.ranosys.autestingdemo.data.user

import com.example.ranosys.autestingdemo.data.car.Car
import io.reactivex.Observable


/**
 * @author Hitesh Khatri
 */
interface UserDataSource {

    fun getUsers(): Observable<MutableList<User>>

    fun getUser(id:String):Observable<User>

    fun addUser(user: User)

    fun editUser(user: User)

    fun deleteUser(user: User)

    fun deleteUser(userId: String)

    fun deleteAllUsers()

    fun refreshUser()
}