package com.example.ranosys.autestingdemo.users

import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.amitshekhar.DebugDB
import com.example.ranosys.autestingdemo.R
import com.example.ranosys.autestingdemo.utils.AppConstants

/**
 *
 * @author Hitesh Khatri
 */
class UserActivity :AppCompatActivity() {

    var userFragment = UserFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        Log.d("DatabaseID", DebugDB.getAddressLog());

        userFragment = UserFragment()

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.container, userFragment, AppConstants.USER_FRAGMENT)
        fragmentTransaction.commit()
    }

    fun onAddUserClicked(view: View){
        userFragment.onAddUserClicked(view)
    }
}