package com.example.ranosys.autestingdemo.addedituser

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.ranosys.autestingdemo.data.car.Car

/**
 * @author Hitesh Khatri
 */
class AddEditCarAdapter(val cars:MutableList<Car>,
                        val presenter: AddEditUserContractor.Presenter)
    : BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItem(position: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(position: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}