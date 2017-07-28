package com.example.ranosys.autestingdemo.users

import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.example.ranosys.autestingdemo.R
import com.example.ranosys.autestingdemo.data.user.User

/**
 * @author Hitesh Khatri
 */
class UserAdapter(list: MutableList<User>, presenter:UserContract.Presenter) : BaseAdapter() {

    private var list:MutableList<User>?=null

    private var presenter:UserContract.Presenter

    init {
        setList(list)
        this.presenter=presenter
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder:ViewHolder
        val view:View
        if (null == convertView){
            val inflater = LayoutInflater.from(parent?.context)
            view = inflater.inflate(R.layout.layout_item_user,parent,false)
            viewHolder = ViewHolder(view)
            view.setTag(viewHolder)
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        viewHolder.userName.setText(list?.get(position)?.name)

        viewHolder.parentLayout.setOnClickListener({
            presenter.openUserDetail(list!!.get(position))
        })

        return view

    }

    override fun getItem(position: Int): Any {
        return this.list!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        if (null!=this.list){
            return this.list!!.size
        }else{
            return 0
        }
    }

    fun swapData(list:MutableList<User>){
        setList(list)
        notifyDataSetChanged() }

    fun setList(list: MutableList<User>){
        this.list=list }

    inner class ViewHolder(val view: View){
        var userName:TextView
        var parentLayout: LinearLayout
        init {
            userName = view.findViewById(R.id.tv_user) as TextView
            parentLayout=view.findViewById(R.id.parent_layout) as LinearLayout
        } }

}