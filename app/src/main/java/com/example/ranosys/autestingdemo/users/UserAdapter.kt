package com.example.ranosys.autestingdemo.users

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.ranosys.autestingdemo.data.user.User

/**
 * @author Hitesh Khatri
 */
class UserAdapter(list: MutableList<User>, itemClickListener: UserItemClickListener) : BaseAdapter() {
    private var list:MutableList<User>?=null
    private var itemClick:UserItemClickListener

    init {
        setList(list)
        this.itemClick=itemClickListener
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItem(position: Int): Any {
        return this.list!!.get(position)
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
        notifyDataSetChanged()
    }

    fun setList(list: MutableList<User>){
        this.list=list
    }

    infix fun<T> Boolean.then(param:T):T?= if (this) param else null
}