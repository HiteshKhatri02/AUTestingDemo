package com.example.ranosys.autestingdemo.data.user

import android.os.Parcel
import android.os.Parcelable
import java.util.*


/**
 * @author Hitesh Khatri
 */
data class User(
        var id:String,
        var name:String,
        var address:String) : Parcelable {

    fun User(name: String, address: String) = User(UUID.randomUUID().toString(), name, address)

    fun User() = User("","", "")

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
    source.readString(),
    source.readString(),
    source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(name)
        dest.writeString(address)
    }
}