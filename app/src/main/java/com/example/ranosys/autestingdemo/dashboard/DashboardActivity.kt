package com.example.ranosys.autestingdemo.dashboard

import android.os.Bundle
import android.support.v4.app.FragmentTabHost
import android.support.v7.app.AppCompatActivity
import android.widget.TabHost
import com.example.ranosys.autestingdemo.R
import com.example.ranosys.autestingdemo.users.UserFragment

/**
 *
 * @author Hitesh Khatri
 */
class DashboardActivity :AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val tabHost:FragmentTabHost=findViewById(android.R.id.tabhost)as FragmentTabHost
        tabHost.setup(this,supportFragmentManager,android.R.id.tabcontent)

        tabHost.addTab(tabHost.newTabSpec("user").setIndicator("USER"),UserFragment::class.java,null)

    }
}