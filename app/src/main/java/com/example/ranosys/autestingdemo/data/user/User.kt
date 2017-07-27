package com.example.ranosys.autestingdemo.data.user

import android.os.Parcel
import android.os.Parcelable
import android.util.SparseArray

/**
 * @author Hitesh Khatri
 */
data class User(
        val id:String,
        val name:String,
        val address:String,
        val carIds:String,
        val carSparseArray: SparseArray<Any>) : Parcelable {

    override fun toString(): String {
        return "User(id='$id', name='$name', address='$address', carIds='$carIds', carSparseArray=$carSparseArray)"
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
    source.readString(),
    source.readString(),
    source.readString(),
    source.readString(),
    source.readSparseArray(ClassLoader.getSystemClassLoader())
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(name)
        dest.writeString(address)
        dest.writeString(carIds)
        dest.writeSparseArray(carSparseArray)
    }
}